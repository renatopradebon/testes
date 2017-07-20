package py.com.datapar.maestro.domain.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.produto.model.Produto;

import javax.persistence.*;
import java.util.List;

/**
 * The type Config schema.
 */
@Entity
public class ConfigSchema extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String schemaName;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private boolean schemaTestEnabled;

    @OneToOne(optional = true, orphanRemoval = false, targetEntity = ConfigSchemaAppRevision.class)
    @JsonIgnore
    private ConfigSchemaAppRevision latestAppRevision;

    @OneToOne(optional = true, orphanRemoval = false, targetEntity = ConfigSchemaDbRevision.class)
    @JsonIgnore
    private ConfigSchemaDbRevision latestDbRevision;

    @ManyToOne(optional = false, targetEntity = ServicoApp.class)
    @JsonIgnore
    private ServicoApp servicoAPP;

    @ManyToOne(optional = false, targetEntity = Produto.class)
    @JsonIgnore
    private Produto produto;

    @ManyToOne(optional = false, targetEntity = Filial.class)
    @JsonIgnore
    private Filial filial;

    @ManyToOne(optional = true, targetEntity = ServicoDb.class)
    @JsonIgnore
    private ServicoDb servicoDB;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ConfigSchemaAppRevision.class, orphanRemoval = false, mappedBy = "configSchema")
    @JsonIgnore
    private List<ConfigSchemaAppRevision> appRevisions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ConfigSchemaDbRevision.class, orphanRemoval = false, mappedBy = "configSchema")
    @JsonIgnore
    private List<ConfigSchemaDbRevision> dbRevisions;

    public String getSchemaName() {
        return this.schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public ConfigSchema schemaName(String schemaName) {
        this.schemaName = schemaName;
        return this;
    }

    public boolean isSchemaTestEnabled() {
        return this.schemaTestEnabled;
    }

    public void setSchemaTestEnabled(boolean schemaTestEnabled) {
        this.schemaTestEnabled = schemaTestEnabled;
    }

    public ConfigSchema schemaTestEnabled(boolean schemaTestEnabled) {
        this.schemaTestEnabled = schemaTestEnabled;
        return this;
    }

    public ConfigSchemaAppRevision getLatestAppRevision() {
        return this.latestAppRevision;
    }

    public void setLatestAppRevision(ConfigSchemaAppRevision latestAppRevision) {
        this.latestAppRevision = latestAppRevision;
    }

    public ConfigSchema latestAppRevision(ConfigSchemaAppRevision latestAppRevision) {
        this.latestAppRevision = latestAppRevision;
        return this;
    }

    public ConfigSchemaDbRevision getLatestDbRevision() {
        return this.latestDbRevision;
    }

    public void setLatestDbRevision(ConfigSchemaDbRevision latestDbRevision) {
        this.latestDbRevision = latestDbRevision;
    }

    public ConfigSchema latestDbRevision(ConfigSchemaDbRevision latestDbRevision) {
        this.latestDbRevision = latestDbRevision;
        return this;
    }

    public ServicoApp getServicoAPP() {
        return this.servicoAPP;
    }

    public void setServicoAPP(ServicoApp servicoAPP) {
        this.servicoAPP = servicoAPP;
    }

    public ConfigSchema servicoAPP(ServicoApp servicoAPP) {
        this.servicoAPP = servicoAPP;
        return this;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ConfigSchema produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public Filial getFilial() {
        return this.filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public ConfigSchema filial(Filial filial) {
        this.filial = filial;
        return this;
    }

    public ServicoDb getServicoDB() {
        return this.servicoDB;
    }

    public void setServicoDB(ServicoDb servicoDB) {
        this.servicoDB = servicoDB;
    }

    public ConfigSchema servicoDB(ServicoDb servicoDB) {
        this.servicoDB = servicoDB;
        return this;
    }

    public List<ConfigSchemaAppRevision> getAppRevisions() {
        return this.appRevisions;
    }

    public void setAppRevisions(List<ConfigSchemaAppRevision> appRevisions) {
        this.appRevisions = appRevisions;
    }

    public ConfigSchema appRevisions(List<ConfigSchemaAppRevision> appRevisions) {
        this.appRevisions = appRevisions;
        return this;
    }

    public List<ConfigSchemaDbRevision> getDbRevisions() {
        return this.dbRevisions;
    }

    public void setDbRevisions(List<ConfigSchemaDbRevision> dbRevisions) {
        this.dbRevisions = dbRevisions;
    }

    public ConfigSchema dbRevisions(List<ConfigSchemaDbRevision> dbRevisions) {
        this.dbRevisions = dbRevisions;
        return this;
    }

    @Override
    public String toString() {
        return "ConfigSchema{" +
                "schemaName='" + schemaName + '\'' +
                ", servicoAPP=" + servicoAPP.toString() +
                ", produto=" + produto.getNome() +
                ", filial=" + filial.getEmpresa().getNome() + "::"+filial.getNome() +
                '}';
    }

    /**
     * Get hash code string.
     *
     * @return the string com "HashCode" / valores unicos para distinguir uma config duplicada.
     */
    @JsonIgnore
    public String getHashCode(){
        return "ConfigSchema{" + "schemaName='" + schemaName + '\'' +
                ", servicoAPP=" + servicoAPP.toString() +
                ", produto=" + produto.getNome()+
                "}";
    }
}
