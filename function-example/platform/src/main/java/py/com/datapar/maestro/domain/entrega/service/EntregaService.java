package py.com.datapar.maestro.domain.entrega.service;

import py.com.datapar.maestro.domain.cliente.model.Empresa;
import py.com.datapar.maestro.domain.commons.model.vo.EntregaEstado;
import py.com.datapar.maestro.domain.commons.model.vo.EstagioExecucaoEstado;
import py.com.datapar.maestro.domain.entrega.dto.EntregaDTO;
import py.com.datapar.maestro.domain.entrega.dto.LogEntrega;
import py.com.datapar.maestro.domain.entrega.model.Entrega;
import py.com.datapar.maestro.domain.entrega.repository.EntregaAlvoRepository;
import py.com.datapar.maestro.domain.entrega.repository.EntregaRepository;
import py.com.datapar.maestro.domain.entrega.repository.EstagioExecucaoEntregaRepository;
import py.com.datapar.maestro.domain.generico.Repository;
import py.com.datapar.maestro.domain.generico.ServiceImpl;
import py.com.datapar.maestro.domain.produto.model.Plano;
import py.com.datapar.maestro.domain.produto.model.Produto;
import py.com.datapar.maestro.domain.produto.repository.PlanoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EntregaService extends ServiceImpl<EntregaRepository, Entrega> {

    @Inject
    private EntregaRepository repository;

    @Inject
    private PlanoRepository planoRepository;

    @Inject
    private EstagioExecucaoEntregaRepository estagioExecucaoEntregaRepository;

    @Inject
    private EntregaAlvoRepository entregaAlvoRepository;

    @Override
    public Repository<Entrega> getRepository() {
        return repository;
    }

    public EntregaService() {
        //todo add rules
    }

    @Override
    public List<Entrega> getAll() {
        return repository
                .get()
                .stream()
                .sorted(Comparator.comparing(Entrega::getDataCriacao).reversed())
                .collect(Collectors.toList());
    }

    public Optional<Entrega> getEntregaById(String uuidEntregaAtual) {
        if (uuidEntregaAtual == null || uuidEntregaAtual.isEmpty())
            return null;

        return getListaEntregas()
                .stream()
                .filter(entrega -> entrega.getId().equals(uuidEntregaAtual))
                .findFirst();
    }

    public List<Empresa> getEmpresasFromQuerie(String querie) {
        TypedQuery<Empresa> query =
                repository.getContext().createQuery(querie, Empresa.class);
        return query.getResultList();
    }

    public Optional<Entrega> getUltimaEntrega(Produto produto) {
        String produtoId = produto.getId();

        return repository
                .getQuery()
                .where(entrega -> entrega.getPlano().getProduto().getId().equals(produtoId))
                .where(entrega -> (entrega.getEstado() == EntregaEstado.EM_EXECUCAO) || (entrega.getEstado() == EntregaEstado.INICIADA))
                .sortedDescendingBy(entrega -> entrega.getDataCriacao())
                .findFirst();
    }

    public List<Entrega> getListaEntregas() {
        return getAll();
    }

    public List<Entrega> getListEntregasByProduto(Produto produto) {
        // todo retornar lista a partir do produto
        return getAll()
                .stream()
                .filter(entrega -> entrega.getPlano().getProduto().getId().equals(produto.getId()))
                .collect(Collectors.toList());
    }

    public List<EntregaDTO> getEntregasDTO() {
        return getAll()
                .stream()
                .map(entrega -> new EntregaDTO(entrega))
                .collect(Collectors.toList());
    }

    /*
    * Utilizado somente no contexto de testes
    * todo remover MOCK após revisão do JPA
    * */
    public List<LogEntrega> getLogEntrega() {

        List<String> conteudo = Arrays.asList("WARN  [org.jboss.as.server.deployment] (MSC service thread 1-4) WFLYSRV0059: Class Path entry lib/json-lib-2.3.jar in /C:/Program Files/wildfly-10.1.0.Final/bin/content/platform-ear-1.0.ear/platform-api-1.0.war  does not point to a valid jar for a Class-Path reference.", "WARN  [org.jboss.as.server.deployment] (MSC service thread 1-4) WFLYSRV0059: Class Path entry log4j-over-slf4j-1.7.2.jar in /C:/Program Files/wildfly-10.1.0.Final/bin/content/platform-ear-1.0.ear/platform-api-1.0.war  does not point to a valid jar for a Class-Path reference.", "WARN  [org.jboss.as.server.deployment] (MSC service thread 1-4) WFLYSRV0059: Class Path entry jboss-logging-3.1.3.GA.jar in /C:/Program Files/wildfly-10.1.0.Final/bin/content/platform-ear-1.0.ear/platform-api-1.0.war  does not point to a valid jar for a Class-Path reference.", "WARNING [javax.enterprise.resource.webcontainer.jsf.flash] (default task-16) JSF1095: The response was already committed by the time we tried to set the outgoing cookie for the flash.  Any values stored to the flash will not be available on the next request.");

        List<EstagioExecucaoEstado> estagioExecucaoEstados = Arrays.asList(EstagioExecucaoEstado.CONCLUIDA,
                EstagioExecucaoEstado.INICIADA,
                EstagioExecucaoEstado.CANCELADA,
                EstagioExecucaoEstado.EM_EXECUCAO,
                EstagioExecucaoEstado.INTERROMPIDA);

        List<String> acoes = Arrays.asList("Teste", "Qualidade", "Produção");

        List<String> conteudos = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(20); i++) {
            //...
            conteudos.addAll(conteudo);
        }

        return acoes
                .stream()
                .parallel()
                .map(texto -> {
                            EstagioExecucaoEstado estado = estagioExecucaoEstados.get(new Random().nextInt(estagioExecucaoEstados.size() - 1));
                            LogEntrega log = new LogEntrega()
                                    .id(String.valueOf(new Random().nextInt()))
                                    .dtFim(Calendar.getInstance().getTime())
                                    .dtInicio(Calendar.getInstance().getTime())
                                    .acao(texto)
                                    .estado(estado.getDescricao())
                                    .conteudo(conteudos);
                            return log;
                        }
                )
                .collect(Collectors.toList());
    }

    public Plano getPlanoDefault() {
        return planoRepository.getQuery().findFirst().get();
    }

    public EstagioExecucaoEntregaRepository getEstagioExecucaoEntregaRepository() {
        return estagioExecucaoEntregaRepository;
    }

    public EntregaAlvoRepository getEntregaAlvoRepository() {
        return entregaAlvoRepository;
    }
}