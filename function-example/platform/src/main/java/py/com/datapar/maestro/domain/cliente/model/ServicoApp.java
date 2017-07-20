package py.com.datapar.maestro.domain.cliente.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.*;
import java.util.List;

@JsonTypeName("servico_app")
@Entity
public class ServicoApp extends Servico {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    private String apptoken;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private int hostPort;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private int hostConsolePort;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ConfigSchema.class, orphanRemoval = false, mappedBy = "servicoAPP")
    //@JsonManagedReference
    private List<ConfigSchema> configSchema;


    public String getApptoken() {
        return this.apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }

    public ServicoApp apptoken(String apptoken) {
        this.apptoken = apptoken;
        return this;
    }

    public int getHostPort() {
        return this.hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public ServicoApp hostPort(int hostPort) {
        this.hostPort = hostPort;
        return this;
    }

    public int getHostConsolePort() {
        return this.hostConsolePort;
    }

    public void setHostConsolePort(int hostConsolePort) {
        this.hostConsolePort = hostConsolePort;
    }

    public ServicoApp hostConsolePort(int hostConsolePort) {
        this.hostConsolePort = hostConsolePort;
        return this;
    }

    public List<ConfigSchema> getConfigSchema() {
        return this.configSchema;
    }

    public void setConfigSchema(List<ConfigSchema> configSchemas) {
        this.configSchema = configSchemas;
    }

    public ServicoApp configSchemas(List<ConfigSchema> configSchemas) {
        this.configSchema = configSchemas;
        return this;
    }

    @Override
    public String toString() {
        return "ServicoApp{" +
                "host=" + this.getServidor().getHostIP() + ":" + String.valueOf(hostPort) +
                '}';
    }
}
