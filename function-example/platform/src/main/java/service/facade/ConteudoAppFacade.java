package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.entrega.model.ConteudoApp;

@Stateless
@Named("conteudoApp")
public class ConteudoAppFacade extends AbstractFacade<ConteudoApp, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConteudoAppFacade() {
        super(ConteudoApp.class);
    }

}
