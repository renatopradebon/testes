package py.com.datapar.maestro.domain.generico;

import org.jinq.jpa.JPAJinqStream;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.commons.model.Identity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class ServiceImpl<R extends Repository, T extends EntityWithIdentity> implements Service<R, T>{

    private List<Rule<T>> rules = new ArrayList<>();
    
    @Override
    public List<Rule<T>> getRules() {
        return rules;
    }

    @Override
    public ServiceResult<T> validate(T entity) {

        //todo o rule retornar o erro ?
        ArrayList<String> errorList = new ArrayList<String>();

        for (Rule<T> rule : getRules()) {
            if (!rule.isValid(entity))
                errorList.add(rule.toString());
        }
        return new ServiceResult<T>( errorList, entity );//
    }

    private ServiceResult<T> validateAndInvoke(T entity, Function<T, T> func){
        ServiceResult result = validate(entity);

        if (!result.hasErrors()) {
            entity = func.apply(entity);
            if (entity != null)
                result.setEntity( entity );
        }

        return result;
    }

    @Override
    public JPAJinqStream<T> getQuery() {
        return getRepository().getQuery();
    }

    @Override
    public ServiceResult<T> grava(T entity) {
        return validateAndInvoke(entity, t -> getRepository().merge(t) );
    }

    @Override
    public ServiceResult<T> deleta(T entity) {
        return validateAndInvoke(entity, t -> {getRepository().remove(t); return null;} );
    }

    @Override
    public T criaNova() throws InstantiationException, IllegalAccessException {
        return getRepository().createNew();
    }

    public RuleBuilder<R, T> ruleBuilder() {
        return new RuleBuilder<R, T>(this);
    }

}
