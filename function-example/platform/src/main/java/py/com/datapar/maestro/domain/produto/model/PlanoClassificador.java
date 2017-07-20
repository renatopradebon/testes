package py.com.datapar.maestro.domain.produto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.commons.model.vo.TipoClassificador;

import javax.persistence.*;

@Entity
public class PlanoClassificador extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @JsonIgnore
    private String queryEmpresas;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private int ordemExecucao;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String nomeClassificador;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    @Enumerated(EnumType.ORDINAL)
    private TipoClassificador tipoClassificador;

    @ManyToOne(optional = true, targetEntity = Plano.class)
    @JsonBackReference
    private Plano plano;

    public String getQueryEmpresas() {
        return this.queryEmpresas;
    }

    public void setQueryEmpresas(String queryEmpresas) {
        this.queryEmpresas = queryEmpresas;
    }

    public PlanoClassificador queryEmpresas(String queryEmpresas) {
        this.queryEmpresas = queryEmpresas;
        return this;
    }

    public int getOrdemExecucao() {
        return this.ordemExecucao;
    }

    public void setOrdemExecucao(int ordemExecucao) {
        this.ordemExecucao = ordemExecucao;
    }

    public PlanoClassificador ordemExecucao(int ordemExecucao) {
        this.ordemExecucao = ordemExecucao;
        return this;
    }

    public String getNomeClassificador() {
        return nomeClassificador;
    }

    public void setNomeClassificador(String nomeClassificador) {
        this.nomeClassificador = nomeClassificador;
    }

    public PlanoClassificador nomeClassificador(String nomeClassificador) {
        this.nomeClassificador = nomeClassificador;
        return this;
    }

    public TipoClassificador getTipoClassificador() {
        return tipoClassificador;
    }

    public PlanoClassificador tipoClassificador(TipoClassificador tipoClassificador) {
        this.tipoClassificador = tipoClassificador;
        return this;
    }

    public void setTipoClassificador(TipoClassificador tipoClassificador) {
        this.tipoClassificador = tipoClassificador;
    }

    public Plano getPlano() {
        return this.plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public PlanoClassificador plano(Plano plano) {
        this.plano = plano;
        return this;
    }

}
