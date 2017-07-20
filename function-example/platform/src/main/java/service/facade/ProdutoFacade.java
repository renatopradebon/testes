package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.produto.model.Produto;

@Stateless
@Named("produto")
public class ProdutoFacade extends AbstractFacade<Produto, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }

}
