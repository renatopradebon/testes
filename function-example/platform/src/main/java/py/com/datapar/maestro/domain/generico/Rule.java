package py.com.datapar.maestro.domain.generico;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

public interface Rule<T extends EntityWithIdentity> {

    boolean isValid(T entity);
}


