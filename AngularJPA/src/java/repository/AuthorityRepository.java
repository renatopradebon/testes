package repository;

import py.com.angularjpa.Authority;

import javax.persistence.EntityManager;
import javax.inject.Inject;

public class AuthorityRepository extends AbstractRepository<Authority, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorityRepository() {
        super(Authority.class);
    }
}
