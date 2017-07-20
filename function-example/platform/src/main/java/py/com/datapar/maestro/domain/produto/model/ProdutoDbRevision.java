package py.com.datapar.maestro.domain.produto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProdutoDbRevision extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String revisaoBanco;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @ElementCollection
    private List<String> scripts;

    @ManyToOne(optional = false, targetEntity = Produto.class)
    @JsonIgnore
    private Produto produto;

    /**
     * Sequence ascending for script's executions
     */
    @Column(unique = false, updatable = false, insertable = true, nullable = false, scale = 0, precision = 0)
    @Basic(optional = false)
    private long ordemExecucao;

    public String getRevisaoBanco() {
        return this.revisaoBanco;
    }

    public void setRevisaoBanco(String revisaoBanco) {
        this.revisaoBanco = revisaoBanco;
    }

    public ProdutoDbRevision revisaoBanco(String revisaoBanco) {
        this.revisaoBanco = revisaoBanco;
        return this;
    }

    public List<String> getScripts() {
        return this.scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }

    public ProdutoDbRevision scripts(List<String> scripts) {
        this.scripts = scripts;
        return this;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoDbRevision produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public long getOrdemExecucao() {
        return ordemExecucao;
    }

    public ProdutoDbRevision ordemExecucao(long ordemExecucao) {
        this.ordemExecucao = ordemExecucao;
        return this;
    }

}
