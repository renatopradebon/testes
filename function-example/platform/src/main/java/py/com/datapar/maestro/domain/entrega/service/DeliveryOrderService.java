package py.com.datapar.maestro.domain.entrega.service;

import py.com.datapar.maestro.domain.entrega.dto.DeliveryOrderAlvoDTO;
import py.com.datapar.maestro.domain.entrega.model.DeliveryOrder;
import py.com.datapar.maestro.domain.entrega.model.DeliveryOrderAlvoServico;
import py.com.datapar.maestro.domain.entrega.repository.DeliveryOrderAlvoServicoRepository;
import py.com.datapar.maestro.domain.entrega.repository.DeliveryOrderRepository;
import py.com.datapar.maestro.domain.generico.Repository;
import py.com.datapar.maestro.domain.generico.ServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DeliveryOrderService extends ServiceImpl<DeliveryOrderRepository, DeliveryOrder> {

    @Inject
    DeliveryOrderRepository repository;

    @Inject
    DeliveryOrderAlvoServicoRepository alvoServicoRepository;


    @Override
    public Repository<DeliveryOrder> getRepository() {
        return repository;
    }

    @Override
    public List<DeliveryOrder> getAll() {
        return repository
                .get()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DeliveryOrder> getById(String id) {
        if (id == null || id.isEmpty())
            return null;

        return getAll()
                .stream()
                .filter(deliveryOrder -> deliveryOrder.getId().equals(id))
                .findFirst();
    }

    public List<DeliveryOrderAlvoServico> getAlvosByDeliveryOrder(String idDeliveryOrder) {
        if (idDeliveryOrder == null || idDeliveryOrder.isEmpty())
            return null;

        return alvoServicoRepository
                .get()
                .stream()
                .filter(deliveryOrderAlvoServico -> deliveryOrderAlvoServico.getDeliveryOrder().getId().equals(idDeliveryOrder))
                .sorted(Comparator.comparing(DeliveryOrderAlvoServico::getSchemaName))
                .collect(Collectors.toList());
    }

    public List<DeliveryOrderAlvoDTO> getAlvosDTOByDeliveryOrder(String idDeliveryOrder) {
        if (idDeliveryOrder == null || idDeliveryOrder.isEmpty())
            return null;

        return getAlvosByDeliveryOrder(idDeliveryOrder)
                .stream()
                .map(deliveryOrderAlvoServico -> new DeliveryOrderAlvoDTO(deliveryOrderAlvoServico))
                .collect(Collectors.toList());
    }

}
