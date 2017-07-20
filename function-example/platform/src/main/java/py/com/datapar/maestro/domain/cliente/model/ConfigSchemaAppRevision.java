package py.com.datapar.maestro.domain.cliente.model;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.produto.model.ProdutoAppVersao;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ConfigSchemaAppRevision extends EntityWithIdentity { 

    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic(optional=false)
    private Timestamp dataUpdated;

    @OneToOne(optional=false,orphanRemoval=false,targetEntity = ProdutoAppVersao.class)
    //@JsonBackReference
    private ProdutoAppVersao produtoAppVersao;

    @ManyToOne(optional=false,targetEntity = ConfigSchema.class)
    //@JsonBackReference
    private ConfigSchema configSchema;


    public Timestamp getDataUpdated() {
        return this.dataUpdated;
    }

    public void setDataUpdated(Timestamp dataUpdated) {
        this.dataUpdated = dataUpdated;
    }

    public ConfigSchemaAppRevision dataUpdated(Timestamp dataUpdated) {
        this.dataUpdated = dataUpdated;
        return this;
    }
    public ProdutoAppVersao getProdutoAppVersao() {
        return this.produtoAppVersao;
    }

    public void setProdutoAppVersao(ProdutoAppVersao produtoAppVersao) {
        this.produtoAppVersao = produtoAppVersao;
    }

    public ConfigSchemaAppRevision produtoAppVersao(ProdutoAppVersao produtoAppVersao) {
        this.produtoAppVersao = produtoAppVersao;
        return this;
    }
    public ConfigSchema getConfigSchema() {
        return this.configSchema;
    }

    public void setConfigSchema(ConfigSchema configSchema) {
        this.configSchema = configSchema;
    }

    public ConfigSchemaAppRevision configSchema(ConfigSchema configSchema) {
        this.configSchema = configSchema;
        return this;
    }

}
