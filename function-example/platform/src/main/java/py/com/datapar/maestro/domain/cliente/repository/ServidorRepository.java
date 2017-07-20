package py.com.datapar.maestro.domain.cliente.repository;

import py.com.datapar.maestro.domain.cliente.model.Servidor;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ServidorRepository extends JpaRepository<Servidor> {
    
    public ServidorRepository() {
        super(Servidor.class);
    }
    
    
    
    
}
