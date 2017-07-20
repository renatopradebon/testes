package py.com.datapar.maestro.domain.entrega.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LogEntrega implements Serializable {
    private String id;
    private String acao;
    private String estado;
    private Date dtInicio;
    private Date dtFim;
    private List<String> conteudo;

    public LogEntrega id(String id) {
        this.id = id;
        return this;
    }

    public LogEntrega acao(String acao) {
        this.acao = acao;
        return this;
    }

    public LogEntrega dtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
        return this;
    }

    public LogEntrega dtFim(Date dtFim) {
        this.dtFim = dtFim;
        return this;
    }

    public LogEntrega conteudo(List<String> conteudo) {
        this.conteudo = conteudo;
        return this;
    }

    public LogEntrega estado(String estado) {
        this.estado = estado;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getAcao() {
        return acao;
    }

    public String getDtInicio() {
        return dtInicio.toString();
    }

    public String getDtFim() {
        return dtFim.toString();
    }

    public List<String> getConteudo() {
        return conteudo;
    }

    public String getEstado() {
        return estado;
    }
}
