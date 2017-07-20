package py.com.datapar.maestro.domain.commons.model;

import py.com.datapar.maestro.domain.cliente.model.ServicoApp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Configurações gerais do servlet.
 * Ex.: A nivel de Master, conterá o host:porta do plataforma.
 */
@Entity
public class MaestroAppConfig extends EntityWithIdentity {

    /**
     * Host e porta do Plataforma
     */
    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    private String maestroplatURI;

    /**
     * Referência do serviço master/slave que está rodando nessa instância.
     * Obs.: Quando nulo, está em modo de configuração.
     */
    @OneToOne(optional = true, orphanRemoval = false, targetEntity = ServicoApp.class)
    private ServicoApp servicoMaestroApp;


    public String getMaestroplatURI() {
        return this.maestroplatURI;
    }

    public void setMaestroplatURI(String maestroplatURI) {
        this.maestroplatURI = maestroplatURI;
    }

    public MaestroAppConfig maestroplatURI(String maestroplatURI) {
        this.maestroplatURI = maestroplatURI;
        return this;
    }

    public ServicoApp getServicoMaestroApp() {
        return this.servicoMaestroApp;
    }

    public void setServicoMaestroApp(ServicoApp servicoMaestroApp) {
        this.servicoMaestroApp = servicoMaestroApp;
    }

    public MaestroAppConfig servicoMaestroApp(ServicoApp servicoMaestroApp) {
        this.servicoMaestroApp = servicoMaestroApp;
        return this;
    }

}
