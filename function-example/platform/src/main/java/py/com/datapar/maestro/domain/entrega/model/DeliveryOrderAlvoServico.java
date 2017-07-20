package py.com.datapar.maestro.domain.entrega.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.cliente.model.ConfigSchema;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.commons.model.vo.DeliveryOrderAlvoEstado;
import py.com.datapar.maestro.domain.commons.model.vo.TipoServidor;

import javax.persistence.*;

@Entity
public class DeliveryOrderAlvoServico extends EntityWithIdentity {

    @Column
    @Basic
    @Enumerated(EnumType.ORDINAL)
    private DeliveryOrderAlvoEstado estado;

    /**
     * Utiliza master ou platform
     */
    @Column
    @Basic
    @Enumerated(EnumType.ORDINAL)
    private TipoServidor encarregadoEntrega;

    @OneToOne(targetEntity = ConfigSchema.class)
    @JsonIgnore
    private ConfigSchema configSchema;

    @ManyToOne(targetEntity = DeliveryOrder.class)
    @JsonIgnore
    private DeliveryOrder deliveryOrder;

    public DeliveryOrderAlvoEstado getEstado() {
        return this.estado;
    }

    public void setEstado(DeliveryOrderAlvoEstado estado) {
        this.estado = estado;
    }

    public DeliveryOrderAlvoServico estado(DeliveryOrderAlvoEstado estado) {
        this.estado = estado;
        return this;
    }

    public TipoServidor getEncarregadoEntrega() {
        return this.encarregadoEntrega;
    }

    public void setEncarregadoEntrega(TipoServidor encarregadoEntrega) {
        this.encarregadoEntrega = encarregadoEntrega;
    }

    public DeliveryOrderAlvoServico encarregadoEntrega(TipoServidor encarregadoEntrega) {
        this.encarregadoEntrega = encarregadoEntrega;
        return this;
    }

    public ConfigSchema getConfigSchema() {
        return this.configSchema;
    }

    public void setConfigSchema(ConfigSchema configSchema) {
        this.configSchema = configSchema;
    }

    public DeliveryOrderAlvoServico configSchema(ConfigSchema configSchema) {
        this.configSchema = configSchema;
        return this;
    }

    public DeliveryOrder getDeliveryOrder() {
        return this.deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public DeliveryOrderAlvoServico deliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
        return this;
    }

    // todo Ver se é a melhor estratégia. Utilizado para ordenação dos schemas de deliveryorder
    @Transient
    public String getSchemaName() {
        return getConfigSchema().getSchemaName();
    }

}
