package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.entrega.model.DeliveryOrderAlvoServico;

@Stateless
@Named("deliveryOrderAlvoServico")
public class DeliveryOrderAlvoServicoFacade extends AbstractFacade<DeliveryOrderAlvoServico, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeliveryOrderAlvoServicoFacade() {
        super(DeliveryOrderAlvoServico.class);
    }

}
