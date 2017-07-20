package py.com.datapar.maestro.domain.commons.repository;

import py.com.datapar.maestro.domain.commons.model.MaestroAppConfig;
import py.com.datapar.maestro.domain.generico.JpaRepository;


public class MaestroAppConfigRepository extends JpaRepository<MaestroAppConfig> {

    public MaestroAppConfigRepository() {

        super(MaestroAppConfig.class);
    }

}