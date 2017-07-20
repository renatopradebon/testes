package py.com.datapar.maestro.domain.commons.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Usuario userId(String userId) {
        this.userId = userId;
        return this;
    }

}
