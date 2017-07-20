package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.produto.model.ProdutoDbRevision;

@Stateless
@Named("produtoDbRevision")
public class ProdutoDbRevisionFacade extends AbstractFacade<ProdutoDbRevision, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoDbRevisionFacade() {
        super(ProdutoDbRevision.class);
    }

}
