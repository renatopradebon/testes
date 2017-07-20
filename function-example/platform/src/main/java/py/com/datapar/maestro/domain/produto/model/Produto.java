package py.com.datapar.maestro.domain.produto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.cliente.model.Cliente;
import py.com.datapar.maestro.domain.cliente.model.ConfigSchema;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.commons.model.vo.BancoUtilizado;
import py.com.datapar.maestro.domain.commons.model.vo.ProtudoTipo;
import py.com.datapar.maestro.lib.helper.XList;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Produto extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @NotNull(message = "El nombre es obligatorio!")
    @Size(max = 255)
    private String nome;

    /**
     * Tipo de Banco Utilizado: ORACLE, POSTGRE, MONGODB ETC
     */
    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @Enumerated(EnumType.ORDINAL)
    private BancoUtilizado bancoUtilizado;

    /**
     * Tipo de Produto: Desktop, WEB, Banco etc
     */
    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    @Enumerated(EnumType.ORDINAL)
    private ProtudoTipo tipoProduto;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ProdutoAppVersao.class, orphanRemoval = false, mappedBy = "produto")
    @JsonIgnore
    private List<ProdutoAppVersao> versoesApp;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ProdutoDbRevision.class, orphanRemoval = false, mappedBy = "produto")
    @JsonIgnore
    private List<ProdutoDbRevision> revisoesDB;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ConfigSchema.class, orphanRemoval = false, mappedBy = "produto")
    @JsonIgnore
    private List<ConfigSchema> configSchemas;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Plano.class, orphanRemoval = false, mappedBy = "produto")
    @JsonIgnore
    private List<Plano> planos;

    @ManyToMany(fetch = FetchType.LAZY, /*NO CASCADE*/ targetEntity = Cliente.class)
    @JsonIgnore
    private List<Cliente> clientes;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 500, scale = 0, precision = 0)
    @Size(max = 500)
    private String pathImagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Produto nome(String nome) {
        this.nome = nome;
        return this;
    }

    public BancoUtilizado getBancoUtilizado() {
        return this.bancoUtilizado;
    }

    public void setBancoUtilizado(BancoUtilizado bancoUtilizado) {
        this.bancoUtilizado = bancoUtilizado;
    }

    public Produto bancoUtilizado(BancoUtilizado bancoUtilizado) {
        this.bancoUtilizado = bancoUtilizado;
        return this;
    }

    public ProtudoTipo getTipoProduto() {
        return this.tipoProduto;
    }

    public void setTipoProduto(ProtudoTipo tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Produto tipoProduto(ProtudoTipo tipoProduto) {
        this.tipoProduto = tipoProduto;
        return this;
    }

    public List<ProdutoAppVersao> getVersoesApp() {
        return this.versoesApp;
    }

    public void setVersoesApp(List<ProdutoAppVersao> versoesApp) {
        this.versoesApp = versoesApp;
    }

    public Produto versoesApp(List<ProdutoAppVersao> versoesApp) {
        this.versoesApp = versoesApp;
        return this;
    }

    public List<ProdutoDbRevision> getRevisoesDB() {
        return this.revisoesDB;
    }

    public void setRevisoesDB(List<ProdutoDbRevision> revisoesDB) {
        this.revisoesDB = revisoesDB;
    }

    public Produto revisoesDB(List<ProdutoDbRevision> revisoesDB) {
        this.revisoesDB = revisoesDB;
        return this;
    }

    public List<ConfigSchema> getConfigSchemas() {
        return this.configSchemas = XList.createIfNull(this.configSchemas);
    }

    public void setConfigSchemas(List<ConfigSchema> configSchemas) {
        this.configSchemas = configSchemas;
    }

    public Produto configSchemas(List<ConfigSchema> configSchemas) {
        this.configSchemas = configSchemas;
        return this;
    }

    public List<Plano> getPlanos() {
        return this.planos = XList.createIfNull(this.planos);
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    public Produto planos(List<Plano> planos) {
        this.planos = planos;
        return this;
    }

    public List<Cliente> getClientes() {
        return this.clientes = XList.createIfNull(this.clientes);
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Produto clientes(List<Cliente> clientes) {
        this.clientes = clientes;
        return this;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    public Produto pathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
        return this;
    }

    public String getPathImagem() {
        return pathImagem;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", bancoUtilizado=" + bancoUtilizado +
                ", tipoProduto=" + tipoProduto +
                ", versoesApp=" + versoesApp +
                ", revisoesDB=" + revisoesDB +
                ", configSchemas=" + configSchemas +
                ", planos=" + planos +
                ", clientes=" + clientes +
                ", pathImagem='" + pathImagem + '\'' +
                '}';
    }

}
