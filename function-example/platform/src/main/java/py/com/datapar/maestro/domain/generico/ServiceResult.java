package py.com.datapar.maestro.domain.generico;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import java.util.List;

public class ServiceResult<T> {

    private final List<String> errorList;

    private T entity;

    public ServiceResult(List<String> errorList, T entity) {
        this.errorList = errorList;
        this.entity = entity;
    }

    public List<String> getErrorList(){
        return errorList;
    }

    public T getEntity() {
        return entity;
    }

    public boolean hasErrors(){
        return errorList.size() > 0;
    }

    public void setEntity(T entity) {

        this.entity = entity;
    }
}
