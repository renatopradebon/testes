package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.DeliveryOrderAlvoServico;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class DeliveryOrderAlvoServicoRepository extends JpaRepository<DeliveryOrderAlvoServico> {

    public DeliveryOrderAlvoServicoRepository() {
        super(DeliveryOrderAlvoServico.class);
    }
}