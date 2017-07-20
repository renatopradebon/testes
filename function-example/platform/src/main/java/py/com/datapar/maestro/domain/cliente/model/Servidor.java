package py.com.datapar.maestro.domain.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Servidor extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String hostIP;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String contato;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    private String observacao;

    @ManyToOne(optional = false, targetEntity = Cliente.class)
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Servico.class, orphanRemoval = false, mappedBy = "servidor")
    private List<Servico> servicos;

    public String getHostIP() {
        return this.hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public Servidor hostIP(String hostIP) {
        this.hostIP = hostIP;
        return this;
    }

    public String getContato() {
        return this.contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Servidor contato(String contato) {
        this.contato = contato;
        return this;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Servidor observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servidor cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public List<Servico> getServicos() {
        return this.servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Servidor servicos(List<Servico> servicos) {
        this.servicos = servicos;
        return this;
    }

}
