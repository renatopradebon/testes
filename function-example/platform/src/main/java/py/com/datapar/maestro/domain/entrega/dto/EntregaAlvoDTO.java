package py.com.datapar.maestro.domain.entrega.dto;

import py.com.datapar.maestro.domain.cliente.model.Empresa;
import py.com.datapar.maestro.domain.commons.model.vo.EntregaAlvoEstado;
import py.com.datapar.maestro.domain.entrega.model.EntregaAlvo;
import py.com.datapar.maestro.lib.helper.XDate;

import java.io.Serializable;

public class EntregaAlvoDTO implements Serializable {

    private static final long serialVersionUID = -1813954496020041424L;

    private EntregaAlvo entregaAlvo;

    public EntregaAlvoDTO(EntregaAlvo entregaAlvo) {
        this.entregaAlvo = entregaAlvo;
    }

    public EntregaAlvo getEntregaAlvo() {
        return entregaAlvo;
    }

    public void setEntregaAlvo(EntregaAlvo entregaAlvo) {
        this.entregaAlvo = entregaAlvo;
    }

    public String getEstagioAlvo() {
        return getEntregaAlvo().getEstagio().getNome();
    }

    public String getEmpresaAlvo() {
        return getEntregaAlvo().getEmpresa().getNome();
    }

    public String getEstadoAlvoString() {
        return getEntregaAlvo().getEstado().getDescricao();
    }

    public EntregaAlvoEstado getEstadoAlvo() {
        return getEntregaAlvo().getEstado();
    }

    public String getStatusAutorizacao() {
        return getEntregaAlvo().isRequerAutorizacao() == true ? "Autorizado" : "No autorizado";
    }

    public String getAutorizadoPor() {
        // TODO Adicionar retorno do modelo de Alvo quando existir
        return "Fulano";
    }

    public String getDataAutorizacao() {
        // TODO Adicionar retorno do modelo de Alvo quando existir
        return XDate.formatDateHour(getEntregaAlvo().getDataAgendamento());
    }

    public String getDataAgendamento() {
        return XDate.formatDateHour(getEntregaAlvo().getDataAgendamento());
    }

    public String getDataExecucaoEntrega() {
        // TODO Adicionar retorno do modelo de Alvo quando existir
        return XDate.formatDateHour(getEntregaAlvo().getDataAgendamento());
    }

    public String getEntregaId() {
        return getEntregaAlvo()
                .getEstagio()
                .getEntrega()
                .getId();
    }

    public Empresa getEmpresa() {
        return getEntregaAlvo().getEmpresa();
    }

}
