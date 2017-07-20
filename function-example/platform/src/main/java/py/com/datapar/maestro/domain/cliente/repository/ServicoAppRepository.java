package py.com.datapar.maestro.domain.cliente.repository;

import py.com.datapar.maestro.domain.cliente.model.ServicoApp;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ServicoAppRepository extends JpaRepository<ServicoApp> {

    public ServicoAppRepository() {
        super(ServicoApp.class);
    }

}