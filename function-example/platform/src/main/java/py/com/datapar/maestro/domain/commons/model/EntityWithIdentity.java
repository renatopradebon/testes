package py.com.datapar.maestro.domain.commons.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import py.com.datapar.maestro.domain.commons.model.vo.Situation;
import py.com.datapar.maestro.domain.entrega.model.EstagioExecucaoEntrega;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public abstract class EntityWithIdentity implements Identity, Comparable<EntityWithIdentity> {

    @Column(unique = true, updatable = false, insertable = true, nullable = false, length = 36, scale = 0, precision = 0)
    @Id
    @Size(min = 16, max = 36)
    private String id;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @Enumerated(EnumType.ORDINAL)
    private Situation situation = Situation.ATIVO;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Version
    @JsonIgnore
    private Long version;

    @PrePersist
    private void onCreate() {
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntityWithIdentity id(String id) {
        this.id = id;
        return this;
    }

    public Situation getSituation() {
        return this.situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public EntityWithIdentity situation(Situation situation) {
        this.situation = situation;
        return this;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public EntityWithIdentity version(Long version) {
        this.version = version;
        return this;
    }
    
    @Override
    public int compareTo(EntityWithIdentity entityWithIdentity) {
        return this.id.compareTo(entityWithIdentity.id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        EntityWithIdentity outro = getClass().cast(o);

        return outro.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }


}
