package py.com.datapar.maestro.domain.cliente.repository;

import py.com.datapar.maestro.domain.cliente.model.ConfigSchemaDbRevision;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ConfigSchemaDbRevisionRepository extends JpaRepository<ConfigSchemaDbRevision> {

    public ConfigSchemaDbRevisionRepository() {
        super(ConfigSchemaDbRevision.class);
    }

}