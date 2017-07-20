package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.produto.model.PlanoClassificador;

@Stateless
@Named("planoClassificador")
public class PlanoClassificadorFacade extends AbstractFacade<PlanoClassificador, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanoClassificadorFacade() {
        super(PlanoClassificador.class);
    }

}
