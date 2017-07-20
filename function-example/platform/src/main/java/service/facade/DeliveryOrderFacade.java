package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.entrega.model.DeliveryOrder;

@Stateless
@Named("deliveryOrder")
public class DeliveryOrderFacade extends AbstractFacade<DeliveryOrder, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeliveryOrderFacade() {
        super(DeliveryOrder.class);
    }

}
