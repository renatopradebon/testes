package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.EstagioExecucaoEntrega;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class EstagioExecucaoEntregaRepository extends JpaRepository<EstagioExecucaoEntrega> {

    public EstagioExecucaoEntregaRepository() {
        super(EstagioExecucaoEntrega.class);
    }
}