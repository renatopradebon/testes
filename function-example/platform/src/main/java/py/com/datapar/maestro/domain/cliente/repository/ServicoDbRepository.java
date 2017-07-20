package py.com.datapar.maestro.domain.cliente.repository;

import py.com.datapar.maestro.domain.cliente.model.ServicoDb;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ServicoDbRepository extends JpaRepository<ServicoDb> {

    public ServicoDbRepository() {
        super(ServicoDb.class);
    }

}