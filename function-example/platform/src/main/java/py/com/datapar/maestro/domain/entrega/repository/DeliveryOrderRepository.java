package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.DeliveryOrder;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class DeliveryOrderRepository extends JpaRepository<DeliveryOrder> {

    public DeliveryOrderRepository() {
        super(DeliveryOrder.class);
    }
}