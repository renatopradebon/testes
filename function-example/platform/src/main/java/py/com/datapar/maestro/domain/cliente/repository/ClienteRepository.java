package py.com.datapar.maestro.domain.cliente.repository;

import py.com.datapar.maestro.domain.cliente.model.Cliente;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ClienteRepository extends JpaRepository<Cliente> {

    public ClienteRepository() {
        super(Cliente.class);
    }

}