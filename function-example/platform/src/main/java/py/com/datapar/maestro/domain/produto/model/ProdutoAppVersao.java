package py.com.datapar.maestro.domain.produto.model;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProdutoAppVersao extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String versaoProduto;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String revisaoMinimaDB;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "ARTEFATO_VERSAO",
            joinColumns = @JoinColumn(name = "ARTEFATO_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "VERSAO_ID", referencedColumnName = "ID"))
    private List<Artefato> artefatos;

    @ManyToOne(optional = false, targetEntity = Produto.class)
    //@JsonBackReference
    private Produto produto;

    /**
     * Sequence ascending for script's executions
     */
    @Column(unique = false, updatable = false, insertable = true, nullable = false, scale = 0, precision = 0)
    @Basic(optional = false)
    private long ordemExecucao;

    public String getVersaoProduto() {
        return this.versaoProduto;
    }

    public void setVersaoProduto(String versaoProduto) {
        this.versaoProduto = versaoProduto;
    }

    public ProdutoAppVersao versaoProduto(String versaoProduto) {
        this.versaoProduto = versaoProduto;
        return this;
    }

    public String getRevisaoMinimaDB() {
        return this.revisaoMinimaDB;
    }

    public void setRevisaoMinimaDB(String revisaoMinimaDB) {
        this.revisaoMinimaDB = revisaoMinimaDB;
    }

    public ProdutoAppVersao revisaoMinimaDB(String revisaoMinimaDB) {
        this.revisaoMinimaDB = revisaoMinimaDB;
        return this;
    }

    public List<Artefato> getArtefatos() {
        return this.artefatos;
    }

    public void setArtefatos(List<Artefato> artefatos) {
        this.artefatos = artefatos;
    }

    public ProdutoAppVersao artefatos(List<Artefato> artefatos) {
        this.artefatos = artefatos;
        return this;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoAppVersao produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public long getOrdemExecucao() {
        return ordemExecucao;
    }

    public void setOrdemExecucao(long ordemExecucao) {
        this.ordemExecucao = ordemExecucao;
    }

    public ProdutoAppVersao ordemExecucao(long ordemExecucao) {
        this.ordemExecucao = ordemExecucao;
        return this;
    }
}
