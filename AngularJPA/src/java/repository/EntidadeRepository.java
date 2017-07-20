package repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.angularjpa.Entidade;

public class EntidadeRepository extends AbstractRepository<Entidade, Long> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntidadeRepository() {
        super(Entidade.class);
    }

}
