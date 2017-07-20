package py.com.datapar.maestro.domain.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import py.com.datapar.maestro.domain.commons.model.vo.BancoUtilizado;
import py.com.datapar.maestro.domain.resources.DomainConts;
import py.com.datapar.maestro.lib.helper.XFormater;

import javax.persistence.*;
import java.util.List;

import static py.com.datapar.maestro.domain.resources.DomainConts.CLIENTE_SERVIDOR_HOST;

@JsonTypeName("servico_db")
@Entity
public class ServicoDb extends Servico {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @JsonIgnore
    private String userdbaLogin;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @JsonIgnore
    private String userdbaPassword;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @JsonIgnore
    private String connectionURL = String.format("jdbc:oracle:thin:@%1s:1521:XE", XFormater.formatAsVariable(CLIENTE_SERVIDOR_HOST));

    /**
     * Tipo de Banco Utilizado: ORACLE, POSTGRE, MONGODB ETC
     */
    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @Enumerated(EnumType.ORDINAL)
    private BancoUtilizado bancoUtilizado;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ConfigSchema.class, orphanRemoval = false, mappedBy = "servicoDB")
    @JsonManagedReference
    private List<ConfigSchema> configSchemas;

    public String getUserdbaLogin() {
        return this.userdbaLogin;
    }

    public void setUserdbaLogin(String userdbaLogin) {
        this.userdbaLogin = userdbaLogin;
    }

    public ServicoDb userdbaLogin(String userdbaLogin) {
        this.userdbaLogin = userdbaLogin;
        return this;
    }

    public String getUserdbaPassword() {
        return this.userdbaPassword;
    }

    public void setUserdbaPassword(String userdbaPassword) {
        this.userdbaPassword = userdbaPassword;
    }

    public ServicoDb userdbaPassword(String userdbaPassword) {
        this.userdbaPassword = userdbaPassword;
        return this;
    }

    public String getConnectionURL() {
        return this.connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public ServicoDb connectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
        return this;
    }

    public BancoUtilizado getBancoUtilizado() {
        return this.bancoUtilizado;
    }

    public void setBancoUtilizado(BancoUtilizado bancoUtilizado) {
        this.bancoUtilizado = bancoUtilizado;
    }

    public ServicoDb bancoUtilizado(BancoUtilizado bancoUtilizado) {
        this.bancoUtilizado = bancoUtilizado;
        return this;
    }

    public List<ConfigSchema> getConfigSchemas() {
        return this.configSchemas;
    }

    public void setConfigSchemas(List<ConfigSchema> configSchemas) {
        this.configSchemas = configSchemas;
    }

    public ServicoDb configSchemas(List<ConfigSchema> configSchemas) {
        this.configSchemas = configSchemas;
        return this;
    }

    @Override
    public String toString() {
        return "ServicoDb{" +
                "userdbaLogin='" + userdbaLogin + '\'' +
                ", connectionURL='" + connectionURL + '\'' +
                ", bancoUtilizado=" + bancoUtilizado +
                '}';
    }

    @JsonIgnore
    @Transient
    public String buildConnectionURL() {
        String hostIp = this.getServidor().getHostIP();
        return connectionURL.replaceAll(XFormater.formatAsVariable(CLIENTE_SERVIDOR_HOST), hostIp);
    }
}
