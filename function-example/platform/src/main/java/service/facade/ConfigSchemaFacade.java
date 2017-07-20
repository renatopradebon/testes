package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.cliente.model.ConfigSchema;

@Stateless
@Named("configSchema")
public class ConfigSchemaFacade extends AbstractFacade<ConfigSchema, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfigSchemaFacade() {
        super(ConfigSchema.class);
    }

}
