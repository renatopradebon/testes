package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.entrega.model.EstagioExecucaoEntrega;

@Stateless
@Named("estagioExecucaoEntrega")
public class EstagioExecucaoEntregaFacade extends AbstractFacade<EstagioExecucaoEntrega, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstagioExecucaoEntregaFacade() {
        super(EstagioExecucaoEntrega.class);
    }

}
