package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.cliente.model.ServicoDb;

@Stateless
@Named("servicoDb")
public class ServicoDbFacade extends AbstractFacade<ServicoDb, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicoDbFacade() {
        super(ServicoDb.class);
    }

}
