package py.com.datapar.maestro.domain.cliente.model;

import com.fasterxml.jackson.annotation.*;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.entrega.model.ConteudoApp;
import py.com.datapar.maestro.domain.entrega.model.ConteudoCommand;
import py.com.datapar.maestro.domain.entrega.model.ConteudoDb;

import javax.persistence.*;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ServicoApp.class, name = "servico_app"),
        @JsonSubTypes.Type(value = ServicoDb.class, name = "servico_db")
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Servico extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    private String maestroAcessKey;

    @ManyToOne(optional = true, targetEntity = Servidor.class)
    //@JsonBackReference
    private Servidor servidor;

    @ManyToOne(optional = true, targetEntity = Servico.class)
    //@JsonManagedReference(value = "parent")
    private Servico parent;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Servico.class, orphanRemoval = false, mappedBy = "parent")
    //@JsonManagedReference(value = "child")
    private List<Servico> childList;

    public String getMaestroAcessKey() {
        return this.maestroAcessKey;
    }

    public void setMaestroAcessKey(String maestroAcessKey) {
        this.maestroAcessKey = maestroAcessKey;
    }

    public Servico maestroAcessKey(String maestroAcessKey) {
        this.maestroAcessKey = maestroAcessKey;
        return this;
    }

    public Servidor getServidor() {
        return this.servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Servico servidor(Servidor servidor) {
        this.servidor = servidor;
        return this;
    }

    public Servico getParent() {
        return this.parent;
    }

    public void setParent(Servico parent) {
        this.parent = parent;
    }

    public Servico parent(Servico parent) {
        this.parent = parent;
        return this;
    }

    public List<Servico> getChildList() {
        return this.childList;
    }

    public void setChildList(List<Servico> childList) {
        this.childList = childList;
    }

    public Servico childList(List<Servico> childList) {
        this.childList = childList;
        return this;
    }

}
