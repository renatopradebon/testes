package py.com.datapar.maestro.domain.generico;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import java.util.function.Function;

public class RuleImpl<T extends EntityWithIdentity> implements Rule<T> {

    private Function<T, Boolean> rule;
    private String errorMessage;

    public RuleImpl() {
    }

    public RuleImpl(Function<T, Boolean> rule, String errorMessage){
        this.errorMessage = errorMessage;
        setRule(rule);
    }

    public void setRule(Function<T, Boolean> rule){

        this.rule = rule;
    }

    @Override
    public boolean isValid(T entity) {
        return rule.apply(entity);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
