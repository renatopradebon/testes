package py.com.datapar.maestro.domain.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Filial extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private long codigo;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String nome;

    @ManyToOne(optional = false, targetEntity = Empresa.class)
    @JsonIgnore
    private Empresa empresa;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ConfigSchema.class, orphanRemoval = false, mappedBy = "filial")
    @JsonIgnore
    private List<ConfigSchema> configSchemas;


    public long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Filial codigo(long codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Filial nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Filial empresa(Empresa empresa) {
        this.empresa = empresa;
        return this;
    }

    public List<ConfigSchema> getConfigSchemas() {
        return this.configSchemas;
    }

    public void setConfigSchemas(List<ConfigSchema> configSchemas) {
        this.configSchemas = configSchemas;
    }

    public Filial configSchemas(List<ConfigSchema> configSchemas) {
        this.configSchemas = configSchemas;
        return this;
    }

}
