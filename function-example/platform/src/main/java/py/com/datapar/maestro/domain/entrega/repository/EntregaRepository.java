package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.Entrega;
import py.com.datapar.maestro.domain.generico.JpaRepository;


public class EntregaRepository extends JpaRepository<Entrega> {

    public EntregaRepository() {
        super(Entrega.class);
    }
}