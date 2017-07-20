package repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.angularjpa.Lista;

public class ListaRepository extends AbstractRepository<Lista, Long> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaRepository() {
        super(Lista.class);
    }

}
