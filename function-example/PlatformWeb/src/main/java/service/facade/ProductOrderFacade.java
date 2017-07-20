package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.model.ProductOrder;

@Stateless
@Named("productOrder")
public class ProductOrderFacade extends AbstractFacade<ProductOrder, Long> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductOrderFacade() {
        super(ProductOrder.class);
    }

}
