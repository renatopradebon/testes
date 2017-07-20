package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.cliente.model.ConfigSchemaDbRevision;

@Stateless
@Named("configSchemaDbRevision")
public class ConfigSchemaDbRevisionFacade extends AbstractFacade<ConfigSchemaDbRevision, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfigSchemaDbRevisionFacade() {
        super(ConfigSchemaDbRevision.class);
    }

}
