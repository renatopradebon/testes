package py.com.datapar.maestro.domain.entrega.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import py.com.datapar.maestro.domain.cliente.model.Empresa;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.commons.model.vo.EntregaAlvoEstado;
import py.com.datapar.maestro.lib.helper.XDate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class EntregaAlvo extends EntityWithIdentity {

    @Column
    @Basic
    @Enumerated(EnumType.ORDINAL)
    private EntregaAlvoEstado estado;

    @OneToOne(targetEntity = Empresa.class)
    private Empresa empresa;

    @OneToOne(targetEntity = DeliveryOrder.class)
    private DeliveryOrder deliveryOrder;

    @ManyToOne(targetEntity = EstagioExecucaoEntrega.class)
    @JsonBackReference
    private EstagioExecucaoEntrega estagio;

    @Column
    @Basic
    private boolean requerAutorizacao;

    @Column
    @Basic
    private boolean autorizado;

    @Column
    @Basic
    private String autorizadoPor;

    @Column
    @Basic
    private Calendar dataAgendamento;

    public EntregaAlvoEstado getEstado() {
        return this.estado;
    }

    public void setEstado(EntregaAlvoEstado estado) {
        this.estado = estado;
    }

    public EntregaAlvo estado(EntregaAlvoEstado estado) {
        this.estado = estado;
        return this;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EntregaAlvo empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public DeliveryOrder getDeliveryOrder() {
        return this.deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public EntregaAlvo deliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
        return this;
    }

    public EstagioExecucaoEntrega getEstagio() {
        return this.estagio;
    }

    public void setEstagio(EstagioExecucaoEntrega estagio) {
        this.estagio = estagio;
    }

    public EntregaAlvo estagio(EstagioExecucaoEntrega estagio) {
        this.estagio = estagio;
        return this;
    }

    public boolean isRequerAutorizacao() {
        return requerAutorizacao;
    }

    public void setRequerAutorizacao(boolean requerAutorizacao) {
        this.requerAutorizacao = requerAutorizacao;
    }

    public EntregaAlvo requerAutorizacao(boolean requerAutorizacao) {
        this.requerAutorizacao = requerAutorizacao;
        return this;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizacaoSituacao) {
        this.autorizado = autorizacaoSituacao;
    }

    public EntregaAlvo autorizacaoSituacao(boolean autorizacaoSituacao) {
        this.autorizado = autorizacaoSituacao;
        return this;
    }

    public String getAutorizadoPor() {
        return autorizadoPor;
    }

    public void setAutorizadoPor(String autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    public EntregaAlvo autorizadoPor(String autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
        return this;
    }

    // todo Para a data funcionar no primefaces (sem converter e de forma rápida), tive que mudar o tipo de parâmetro do campo de data para "Date". Quando tiver tempo, rever essa questão.
    public Date getDataAgendamento() {
        return dataAgendamento.getTime();
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = XDate.dateToCalendar(dataAgendamento);
    }

    public EntregaAlvo dataAgendamento(Calendar dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
        return this;
    }
}
