package py.com.datapar.maestro.domain.entrega.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.commons.model.vo.EstagioExecucaoEstado;
import py.com.datapar.maestro.domain.produto.model.PlanoClassificador;
import py.com.datapar.maestro.lib.helper.XList;

import javax.persistence.*;
import java.util.List;

@Entity
public class EstagioExecucaoEntrega extends EntityWithIdentity {

    @Column
    @Basic
    @Enumerated(EnumType.ORDINAL)
    private EstagioExecucaoEstado estado;

    @OneToOne(targetEntity = PlanoClassificador.class)
    private PlanoClassificador planoClassificador;

    @ManyToOne(targetEntity = Entrega.class)
    @JsonBackReference
    private Entrega entrega;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = EntregaAlvo.class, mappedBy = "estagio")
    @JsonManagedReference
    private List<EntregaAlvo> alvos;

    public EstagioExecucaoEstado getEstado() {
        return this.estado;
    }

    public void setEstado(EstagioExecucaoEstado estado) {
        this.estado = estado;
    }

    public EstagioExecucaoEntrega estado(EstagioExecucaoEstado estado) {
        this.estado = estado;
        return this;
    }

    public PlanoClassificador getPlanoClassificador() {
        return this.planoClassificador;
    }

    public void setPlanoClassificador(PlanoClassificador planoClassificador) {
        this.planoClassificador = planoClassificador;
    }

    public EstagioExecucaoEntrega classificador(PlanoClassificador planoClassificador) {
        this.planoClassificador = planoClassificador;
        return this;
    }

    public Entrega getEntrega() {
        return this.entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public EstagioExecucaoEntrega entrega(Entrega entrega) {
        this.entrega = entrega;
        return this;
    }

    public List<EntregaAlvo> getAlvos() {
        return this.alvos = XList.createIfNull(this.alvos);
    }

    public void setAlvos(List<EntregaAlvo> alvos) {
        this.alvos = alvos;
    }

    public EstagioExecucaoEntrega alvos(List<EntregaAlvo> alvos) {
        this.alvos = alvos;
        return this;
    }

    @Transient
    public int getOrdemExecucao() {
        return this.getPlanoClassificador().getOrdemExecucao();
    }

    @Transient
    public String getNome() {
        return this.getPlanoClassificador().getNomeClassificador();
    }

}
