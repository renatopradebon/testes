package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.entrega.model.EntregaAlvo;

@Stateless
@Named("entregaAlvo")
public class EntregaAlvoFacade extends AbstractFacade<EntregaAlvo, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntregaAlvoFacade() {
        super(EntregaAlvo.class);
    }

}
