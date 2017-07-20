package py.com.datapar.maestro.domain.generico;

import org.jinq.jpa.JPAJinqStream;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Service<R extends Repository, T extends EntityWithIdentity> extends Serializable {
    Repository<T> getRepository();

    List<Rule<T>> getRules();

    JPAJinqStream<T> getQuery();

    ServiceResult<T> validate(T entity);

    ServiceResult<T> grava(T entity);

    ServiceResult<T> deleta(T entity);

    T criaNova() throws InstantiationException, IllegalAccessException;

    default List<T> getAll() {
        return getQuery().toList();
    }

    default Optional<T> getById(String id) {
        return getQuery()
                .where(t -> t.getId().equals(id))
                .findFirst();
    }

}

