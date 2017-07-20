package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.cliente.model.Filial;

@Stateless
@Named("filial")
public class FilialFacade extends AbstractFacade<Filial, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilialFacade() {
        super(Filial.class);
    }

}
