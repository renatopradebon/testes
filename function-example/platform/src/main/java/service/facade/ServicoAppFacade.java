package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.cliente.model.ServicoApp;

@Stateless
@Named("servicoApp")
public class ServicoAppFacade extends AbstractFacade<ServicoApp, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicoAppFacade() {
        super(ServicoApp.class);
    }

}
