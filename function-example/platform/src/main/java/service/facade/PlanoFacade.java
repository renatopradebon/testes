package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.produto.model.Plano;

@Stateless
@Named("plano")
public class PlanoFacade extends AbstractFacade<Plano, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanoFacade() {
        super(Plano.class);
    }

}
