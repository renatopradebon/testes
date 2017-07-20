package py.com.datapar.maestro.domain.generico;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import java.util.function.Function;

public class RuleBuilder<R extends Repository, T extends EntityWithIdentity> {

    private ServiceImpl<R, T> service;

    public RuleBuilder(ServiceImpl<R, T> service) {
        this.service = service;
    }

    public RuleBuilder<R, T> addRule(Function<T, Boolean> rule) {
        return addRule(rule, "Error validando "+ getClass().toString());
    }

    public RuleBuilder<R, T> addRule(Function<T, Boolean> rule, String errorMessage) {
        service.getRules().add(new RuleImpl<T>( rule, errorMessage ));
        return this;
    }

    public RuleBuilder<R, T> addRule(RuleImpl<T> rule) {
        service.getRules().add( rule );
        return this;
    }
}
