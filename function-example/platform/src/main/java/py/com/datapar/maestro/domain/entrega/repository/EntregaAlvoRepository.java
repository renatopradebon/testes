package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.EntregaAlvo;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class EntregaAlvoRepository extends JpaRepository<EntregaAlvo> {

    public EntregaAlvoRepository() {
        super(EntregaAlvo.class);
    }
}