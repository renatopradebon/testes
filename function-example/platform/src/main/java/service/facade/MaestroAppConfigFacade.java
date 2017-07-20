package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.commons.model.MaestroAppConfig;

@Stateless
@Named("maestroAppConfig")
public class MaestroAppConfigFacade extends AbstractFacade<MaestroAppConfig, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaestroAppConfigFacade() {
        super(MaestroAppConfig.class);
    }

}
