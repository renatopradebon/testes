package py.com.datapar.maestro.domain.cliente.model;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.produto.model.ProdutoDbRevision;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ConfigSchemaDbRevision extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private Timestamp dataUpdated;

    @OneToOne(optional = false, orphanRemoval = false, targetEntity = ProdutoDbRevision.class)
    //@JsonBackReference
    private ProdutoDbRevision produtoDbRevision;

    @ManyToOne(optional = false, targetEntity = ConfigSchema.class)
    //@JsonBackReference
    private ConfigSchema configSchema;


    public Timestamp getDataUpdated() {
        return this.dataUpdated;
    }

    public void setDataUpdated(Timestamp dataUpdated) {
        this.dataUpdated = dataUpdated;
    }

    public ConfigSchemaDbRevision dataUpdated(Timestamp dataUpdated) {
        this.dataUpdated = dataUpdated;
        return this;
    }

    public ProdutoDbRevision getProdutoDbRevision() {
        return this.produtoDbRevision;
    }

    public void setProdutoDbRevision(ProdutoDbRevision produtoDbRevision) {
        this.produtoDbRevision = produtoDbRevision;
    }

    public ConfigSchemaDbRevision produtoDBRevision(ProdutoDbRevision produtoDbRevision) {
        this.produtoDbRevision = produtoDbRevision;
        return this;
    }

    public ConfigSchema getConfigSchema() {
        return this.configSchema;
    }

    public void setConfigSchema(ConfigSchema configSchema) {
        this.configSchema = configSchema;
    }

    public ConfigSchemaDbRevision configSchema(ConfigSchema configSchema) {
        this.configSchema = configSchema;
        return this;
    }

}
