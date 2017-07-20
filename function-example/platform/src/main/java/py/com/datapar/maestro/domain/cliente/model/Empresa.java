package py.com.datapar.maestro.domain.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.entrega.model.DeliveryOrder;
import py.com.datapar.maestro.lib.helper.XList;

import javax.persistence.*;
import java.util.List;

@Entity
public class Empresa extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String nome;

    /**
     * Código interno da empresa na base do Dolphin.
     */
    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @JsonIgnore
    private long codigo;

    /**
     * Informações para que se possa "tagear" as empresas.
     * Ex.: monsanto, arroz, tabaco etc.
     */
    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @ElementCollection
    @JsonIgnore
    private List<String> tags;

    @ManyToOne(optional = false, targetEntity = Cliente.class)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Filial.class, orphanRemoval = false, mappedBy = "empresa")
    @JsonIgnore
    private List<Filial> filiais;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = DeliveryOrder.class, orphanRemoval = false, mappedBy = "empresa")
    @JsonIgnore
    private List<DeliveryOrder> deliveryOrders;


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Empresa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Empresa codigo(long codigo) {
        this.codigo = codigo;
        return this;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Empresa tags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public List<Filial> getFiliais() {
        return this.filiais = XList.createIfNull(this.filiais);
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }

    public Empresa filiais(List<Filial> filiais) {
        this.filiais = filiais;
        return this;
    }

    public List<DeliveryOrder> getDeliveryOrders() {
        return this.deliveryOrders;
    }

    public void setDeliveryOrders(List<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    public Empresa deliveryOrders(List<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
        return this;
    }

}
