package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.entrega.model.Entrega;

@Stateless
@Named("entrega")
public class EntregaFacade extends AbstractFacade<Entrega, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntregaFacade() {
        super(Entrega.class);
    }

}
