package py.com.datapar.maestro.domain.entrega.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.cliente.model.Empresa;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import javax.persistence.*;
import java.util.List;

@Entity
public class DeliveryOrder extends EntityWithIdentity {

    /**
     * Versao, Config,, Etc
     */
    @Column
    @Basic
    private String packageKind;

    @Column
    @Basic
    private String packageData;

    @ManyToOne(targetEntity = Empresa.class)
    @JsonIgnore
    private Empresa empresa;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = DeliveryOrderAlvoServico.class, mappedBy = "deliveryOrder")
    private List<DeliveryOrderAlvoServico> alvos;

    public String getPackageKind() {
        return this.packageKind;
    }

    public void setPackageKind(String packageKind) {
        this.packageKind = packageKind;
    }

    public DeliveryOrder packageKind(String packageKind) {
        this.packageKind = packageKind;
        return this;
    }

    public String getPackageData() {
        return this.packageData;
    }

    public void setPackageData(String packageData) {
        this.packageData = packageData;
    }

    public DeliveryOrder packageData(String packageData) {
        this.packageData = packageData;
        return this;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public DeliveryOrder empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public List<DeliveryOrderAlvoServico> getAlvos() {
        return this.alvos;
    }

    public void setAlvos(List<DeliveryOrderAlvoServico> alvos) {
        this.alvos = alvos;
    }

    public DeliveryOrder alvos(List<DeliveryOrderAlvoServico> alvos) {
        this.alvos = alvos;
        return this;
    }

}