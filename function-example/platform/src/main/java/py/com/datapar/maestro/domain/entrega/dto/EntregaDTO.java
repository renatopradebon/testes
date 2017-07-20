package py.com.datapar.maestro.domain.entrega.dto;

import py.com.datapar.maestro.domain.cliente.model.Empresa;
import py.com.datapar.maestro.domain.commons.model.vo.EstagioExecucaoEstado;
import py.com.datapar.maestro.domain.entrega.model.Entrega;
import py.com.datapar.maestro.domain.entrega.model.EntregaAlvo;
import py.com.datapar.maestro.domain.entrega.model.EstagioExecucaoEntrega;
import py.com.datapar.maestro.domain.entrega.service.test.XRandom;
import py.com.datapar.maestro.lib.helper.XDate;
import py.com.datapar.maestro.lib.helper.XMaestro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntregaDTO implements Serializable {

    private static final long serialVersionUID = -4967372556129314302L;

    private Entrega entrega;

    private String estagioDataInicio;

    private String estagioEstado;

    private String entregaTempoTranscorrido;

    private String situacaoFinalizado;

    private List<Empresa> empresas;

    public EntregaDTO(Entrega entrega) {
        this.entrega = entrega;

        // TODO Adicionar retorno do modelo de Alvo quando existir
        setEstagioDataInicio(XMaestro.XDate.formatDate(XMaestro.XDate.addDays(XRandom.getRandom_().nextInt(60) * -1)));
        setEstagioEstado("En ejecución");
        setSituacaoFinalizado("2 días y 4 horas");
        setEntregaTempoTranscorrido("1 semana, 3 días y 3 horas");
    }

    public String getCodinomeEntrega() {
        return getEntrega().getCodinomeEntrega();
    }

    public String getEntregaCriadaPor() {
        return entrega.getUsuarioCriacao();
    }

    public String getDataCriacaoCompleta() {
        return XDate.formatDate(entrega.getDataCriacao());
    }

    public String getEstadoDaEntrega() {
        return getEntrega().getEstado().getDescricao();
    }

    public String getEstagioAtualEntrega() {
        return getEstagioAtual()
                .getPlanoClassificador()
                .getNomeClassificador();
    }

    public String getEstagioDataInicio() {
        return estagioDataInicio;
    }

    public void setEstagioDataInicio(String estagioDataInicio) {
        this.estagioDataInicio = estagioDataInicio;
    }

    public String getEstagioEstado() {
        return estagioEstado;
    }

    public void setEstagioEstado(String estagioEstado) {
        this.estagioEstado = estagioEstado;
    }

    public String getEntregaTempoTranscorrido() {
        return entregaTempoTranscorrido;
    }

    public void setEntregaTempoTranscorrido(String entregaTempoTranscorrido) {
        this.entregaTempoTranscorrido = entregaTempoTranscorrido;
    }

    public String getSituacaoFinalizado() {
        return situacaoFinalizado;
    }

    public void setSituacaoFinalizado(String situacaoFinalizado) {
        this.situacaoFinalizado = situacaoFinalizado;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public String getPlanoEntrega() {
        return getEntrega().getPlano().getNome();
    }

    public String getDataCriacaoEntrega() {
        return XMaestro.XDate.formatDateHour(getEntrega().getDataCriacao());
    }

    public EstagioExecucaoEntrega getEstagioAtual() {
        List<EstagioExecucaoEntrega> estagios;

        return (estagios = getEstagiosEntrega())
                .stream()
                .filter(estagioExecucaoEntrega -> estagioExecucaoEntrega.getEstado().getId() < EstagioExecucaoEstado.CONCLUIDA.getId())
                .findFirst()
                .orElse(estagios.get(estagios.size() - 1));
    }

    public List<EstagioExecucaoEntrega> getEstagiosEntrega() {
        return getEntrega().getEstagios();
    }

    public List<Empresa> getEmpresas() {
        List<Empresa> empresaList = new ArrayList<>();

        if (getEstagiosEntrega() != null) {
            empresaList = getEstagiosEntrega()
                    .stream()
                    .map(estagioExecucaoEntrega -> estagioExecucaoEntrega.getAlvos())
                    .reduce(new ArrayList<EntregaAlvo>(), (l1, l2) -> {
                        l1.addAll(l2);
                        return l1;
                    })
                    .stream()
                    .map(entregaAlvo -> entregaAlvo.getEmpresa())
                    .collect(Collectors.toList());
//            for (EstagioExecucaoEntrega estagioExecucaoEntregaFor : getEstagiosEntrega()) {
//                if (estagioExecucaoEntregaFor.getAlvos() != null) {
//                    for (EntregaAlvo entregaAlvoFor : estagioExecucaoEntregaFor.getAlvos()) {
//                        empresaList.add(entregaAlvoFor.getEmpresa());
//                    }
//                }
//            }
        }

        return empresaList;
    }

}
