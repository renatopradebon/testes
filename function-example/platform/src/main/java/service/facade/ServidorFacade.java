package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.cliente.model.Servidor;

@Stateless
@Named("servidor")
public class ServidorFacade extends AbstractFacade<Servidor, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServidorFacade() {
        super(Servidor.class);
    }

}
