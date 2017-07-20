package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.cliente.model.ConfigSchemaAppRevision;

@Stateless
@Named("configSchemaAppRevision")
public class ConfigSchemaAppRevisionFacade extends AbstractFacade<ConfigSchemaAppRevision, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfigSchemaAppRevisionFacade() {
        super(ConfigSchemaAppRevision.class);
    }

}
