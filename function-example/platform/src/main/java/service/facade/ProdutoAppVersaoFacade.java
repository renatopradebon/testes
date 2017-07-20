package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.produto.model.ProdutoAppVersao;

@Stateless
@Named("produtoAppVersao")
public class ProdutoAppVersaoFacade extends AbstractFacade<ProdutoAppVersao, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoAppVersaoFacade() {
        super(ProdutoAppVersao.class);
    }

}
