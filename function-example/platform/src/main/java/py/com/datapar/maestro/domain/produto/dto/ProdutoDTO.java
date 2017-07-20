package py.com.datapar.maestro.domain.produto.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.datapar.maestro.domain.entrega.model.Entrega;
import py.com.datapar.maestro.domain.produto.model.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = -8601439333164451912L;

    Logger logger = LoggerFactory.getLogger(ProdutoDTO.class);

    private Produto produto;

    private Entrega ultimaEntrega;

    private String lastVersionBanco;

    private String lastVersionApp;

    private String serversOnline;

    private String serversOffline;

    public ProdutoDTO() {
        // TODO remover MOCK após criar serviço para coleta dessas informações
        setLastVersionBanco("v5.0");
        setLastVersionApp("17.5.0");
        setServersOnline("245");
        setServersOffline("11");
    }

    public String getLastVersionBanco() {
        return lastVersionBanco;
    }

    public void setLastVersionBanco(String lastVersionBanco) {
        this.lastVersionBanco = lastVersionBanco;
    }

    public String getLastVersionApp() {
        return lastVersionApp;
    }

    public void setLastVersionApp(String lastVersionApp) {
        this.lastVersionApp = lastVersionApp;
    }

    public String getServersOnline() {
        return serversOnline;
    }

    public void setServersOnline(String serversOnline) {
        this.serversOnline = serversOnline;
    }

    public String getServersOffline() {
        return serversOffline;
    }

    public void setServersOffline(String serversOffline) {
        this.serversOffline = serversOffline;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setUltimaEntrega(Entrega ultimaEntrega) {
        this.ultimaEntrega = ultimaEntrega;
    }

    public Entrega getUltimaEntrega() {
        return ultimaEntrega;
    }

    public String getProdutoID() {
        return getProduto().getId();
    }

    public String getProdutoNome() {
        return getProduto().getNome();
    }

    public String getProdutoBancoUtilizadoDescricao() {
        return getProduto().getBancoUtilizado().getDescricao();
    }

    public String getProdutoTipoDescricao() {
        return getProduto().getTipoProduto().getDescricao();
    }

    public String getProdutoPathImage() {
        return getProduto().getPathImagem();
    }

    public String getUltimaEntregaID() {
        return ultimaEntrega != null ? ultimaEntrega.getId() : "";
    }

    public String getUltimaEntregaPlanoNome() {
        return ultimaEntrega != null ? ultimaEntrega.getPlano().getNome() : "";
    }

    public String getUltimaEntregaCodinomeEntrega() {
        return ultimaEntrega != null ? ultimaEntrega.getCodinomeEntrega() : "";
    }

    public String getUltimaEntregaCodinomeEntregaSubString() {
        if (ultimaEntrega == null)
            return "";

        Integer lastIndexString = ultimaEntrega.getCodinomeEntrega().length() < 19 ?
                ultimaEntrega.getCodinomeEntrega().length() :
                19;

        return ultimaEntrega.getCodinomeEntrega().substring(0, lastIndexString);
    }


}