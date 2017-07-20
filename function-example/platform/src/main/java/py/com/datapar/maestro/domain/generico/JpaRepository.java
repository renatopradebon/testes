package py.com.datapar.maestro.domain.generico;

import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.commons.model.vo.Situation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class JpaRepository<T extends EntityWithIdentity> implements Repository<T>, AutoCloseable {

    @Inject
    EntityManager em;

    JinqJPAStreamProvider streams;

    @Inject
    UserTransaction userTransaction;

    protected Class<T> entityClassType;

    public JpaRepository() {
    }

    public JpaRepository(Class<T> entityClassType) {
        this.entityClassType = entityClassType;
    }

    @PostConstruct
    private void injetaJinq() {
        streams = new JinqJPAStreamProvider(em.getMetamodel());
    }

    public Class<T> getEntityClassType() {
        assert (entityClassType != null) : "Variavel nula";
        return entityClassType;
    }

    public EntityManager getContext() {
        return em;
    }

    public JPAJinqStream<T> getQuery() {
        return run(entityManager -> {
            return streams.streamAll(getContext(), getEntityClassType());
        });
    }

    @Override
    public Set<T> get() {
        List<T> resultList = run(entityManager -> {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getEntityClassType());

            final Root<T> root = criteria.from(getEntityClassType());
            criteria.select(root);

            final TypedQuery<T> query = entityManager.createQuery(criteria);

            return query.getResultList();
        });

        return new HashSet<>(resultList);
    }

    @Override
    public Optional<T> get(String id) {
        return Optional.ofNullable(run(entityManager -> {
            return entityManager.find(getEntityClassType(), id);
        }));
    }

    @Override
    public T setNewID(T entity) {
        return (T) entity.id(UUID.randomUUID().toString());
    }

    @Override
    public T createNew() {
        T entity = null;

        try {
            entity = entityClassType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return entity;
    }


    @Override
    public T persist(T entity) {
        return runInTransaction(entityManager -> {
            entityManager.persist(entity);
            entityManager.flush();
            return entity;
        });
    }

    @Override
    public T merge(T entity) {
        return runInTransaction(entityManager -> {
            T entity2 = entityManager.merge(entity);
            entityManager.flush();
            entity.id(entity2.getId());//transforma retorno do ID.(nao tem out no java e retorno pode ser ignorado...)
            return entity2;
        });
    }

    @Override
    public void persist(Collection<T> entities) {
        run(entityManager -> {
            entities.forEach(this::persist);
        });
    }

    @Override
    public void remove(T entity) {
        //todo: validar existencia da entidade para evitar acesso violento.
        runInTransaction(entityManager -> {
            if (entity != null) {
                //nao exclui fisicamente, apenas seta inativo
                entity.setSituation(Situation.INATIVO);
                entityManager.merge(entity);
            }
        });

    }

    @Override
    public void remove(String id) {
        final T managedEntity = em.find(getEntityClassType(), id);
        remove(managedEntity);
    }

    @Override
    public void remove(Collection<T> entities) {
        run(entityManager -> {
            entities
                    .stream()
                    .map(T::getId)
                    .map(id -> entityManager.find(getEntityClassType(), id))
                    .filter(Objects::nonNull)
                    .forEach(this::remove);
        });
    }

    @Override
    public void remove(Predicate<T> predicate) {
        remove(get(predicate));
    }

    protected <R> R run(Function<EntityManager, R> function) {
        return function.apply(em);
    }

    protected void run(Consumer<EntityManager> function) {
        run(entityManager -> {
            function.accept(entityManager);
            return null;
        });
    }

    @Transactional
    protected <R> R runInTransaction(Function<EntityManager, R> function) {
        return run(entityManager -> {
            R result = null;
            try {
                if (userTransaction != null) userTransaction.begin();
                result = function.apply(entityManager);
                if (userTransaction != null) userTransaction.commit();
            } catch (NotSupportedException e) {
                e.printStackTrace();
            } catch (SystemException e) {
                e.printStackTrace();
            } catch (HeuristicMixedException e) {
                e.printStackTrace();
            } catch (HeuristicRollbackException e) {
                e.printStackTrace();
            } catch (RollbackException e) {
                e.printStackTrace();
            }
            return result;
        });
    }

    protected void runInTransaction(Consumer<EntityManager> function) {
        runInTransaction(entityManager -> {
            function.accept(entityManager);
            return null;
        });
    }

    @Override
    public void close() throws Exception {

    }

    public static <C extends EntityWithIdentity> JpaRepository<C> newRepository(Class<C> classx) {
        return new JpaRepository<C>(classx);
    }
}
