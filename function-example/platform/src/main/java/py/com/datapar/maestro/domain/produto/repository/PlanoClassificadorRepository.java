package py.com.datapar.maestro.domain.produto.repository;

import py.com.datapar.maestro.domain.generico.JpaRepository;
import py.com.datapar.maestro.domain.produto.model.PlanoClassificador;

public class PlanoClassificadorRepository extends JpaRepository<PlanoClassificador> {

    public PlanoClassificadorRepository() {
        super(PlanoClassificador.class);
    }
    
}
