package py.com.datapar.maestro.domain.entrega.model;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.commons.model.vo.EntregaEstado;
import py.com.datapar.maestro.domain.produto.model.Plano;
import py.com.datapar.maestro.lib.helper.XList;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Entrega extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @Enumerated(EnumType.ORDINAL)
    private EntregaEstado estado;

    /**
     * Nome da entrega.
     * Ex.: MONSANTO, TESAKA, IVA18 etc
     */
    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String codinomeEntrega;

    @ManyToOne(optional = true, targetEntity = Plano.class)
    private Plano plano;

    @OneToMany(targetEntity = EstagioExecucaoEntrega.class, orphanRemoval = false, mappedBy = "entrega")
    private List<EstagioExecucaoEntrega> estagios;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Conteudo.class, orphanRemoval = false)
    private List<Conteudo> conteudos;

    @Column(updatable = false, nullable = false)
    @Basic(optional = false)
    private Calendar dataCriacao;

    @Column
    @Basic
    private Calendar dataFinalizacao;

    @Column(nullable = false)
    @Basic(optional = false)
    private String usuarioCriacao;

    public EntregaEstado getEstado() {
        return this.estado;
    }

    public void setEstado(EntregaEstado estado) {
        this.estado = estado;
    }

    public Entrega estado(EntregaEstado estado) {
        this.estado = estado;
        return this;
    }

    public String getCodinomeEntrega() {
        return this.codinomeEntrega;
    }

    public void setCodinomeEntrega(String codinomeEntrega) {
        this.codinomeEntrega = codinomeEntrega;
    }

    public Entrega codinomeEntrega(String codinomeEntrega) {
        this.codinomeEntrega = codinomeEntrega;
        return this;
    }

    public Plano getPlano() {
        return this.plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public Entrega plano(Plano plano) {
        this.plano = plano;
        return this;
    }

    public List<EstagioExecucaoEntrega> getEstagios() {
        return this.estagios = XList.createIfNull(this.estagios);
    }

    public void setEstagios(List<EstagioExecucaoEntrega> estagios) {
        this.estagios = estagios;
    }

    public Entrega estagios(List<EstagioExecucaoEntrega> estagios) {
        this.estagios = estagios;
        return this;
    }

    public List<Conteudo> getConteudos() {
        return this.conteudos = XList.createIfNull(this.conteudos);
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }

    public Entrega conteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
        return this;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Entrega dataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public Calendar getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Calendar dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Entrega dataFinalizacao(Calendar dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
        return this;
    }

    public String getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public void setUsuarioCriacao(String usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
    }

    public Entrega usuarioCriacao(String usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
        return this;
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "estado=" + estado +
                ", codinomeEntrega='" + codinomeEntrega + '\'' +
                ", plano=" + plano +
                ", estagios=" + estagios +
                ", conteudos=" + conteudos +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}