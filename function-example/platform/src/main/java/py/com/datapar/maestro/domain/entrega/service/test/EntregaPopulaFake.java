package py.com.datapar.maestro.domain.entrega.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.datapar.maestro.domain.cliente.model.Empresa;
import py.com.datapar.maestro.domain.commons.model.vo.EntregaEstado;
import py.com.datapar.maestro.domain.commons.model.vo.TipoClassificador;
import py.com.datapar.maestro.domain.entrega.model.*;
import py.com.datapar.maestro.domain.entrega.repository.EntregaRepository;
import py.com.datapar.maestro.domain.entrega.service.EntregaService;
import py.com.datapar.maestro.domain.entrega.service.test.popula.InitPopulaBase;
import py.com.datapar.maestro.domain.produto.model.*;
import py.com.datapar.maestro.domain.produto.service.ProdutoService;
import py.com.datapar.maestro.lib.helper.XList;
import py.com.datapar.maestro.lib.helper.XMaestro;
import py.com.datapar.maestro.lib.utils.Lazy;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class EntregaPopulaFake implements Serializable {

    private static final long serialVersionUID = -4807242926567983139L;

    private static final int NUM_ENTREGAS_FAKES = 20;

    @Inject
    ProdutoService produtoService;

    @Inject
    EntregaService entregaService;

    @Inject
    EntregaRepository entregaRepository;

    @Inject
    InitPopulaBase initPopulaBase;

    private Lazy<List<Entrega>> listaLazy;

    Logger logger = LoggerFactory.getLogger(EntregaPopulaFake.class);

    static int Major = 17;
    static int Version = 6;
    static int Minor = 2;

    protected synchronized String getNextVersion() {
        if (XRandom.getRandom_().nextBoolean()) Minor++;
        if (XRandom.getRandom_().nextBoolean()) Version++;
        return "v." + Major + "." + Version + "." + Minor;
    }

    public EntregaPopulaFake() {

        logger.info("::ENTREGAPOPULAFAKE::> Criando as entregas");

        listaLazy = new Lazy<>(() -> {
            Optional<Produto> produtoOptional = produtoService.getQuery().findFirst();

            Produto produtoDolphin = produtoOptional.orElseGet(() -> {
                try {
                    initPopulaBase.initPopula();
                    return initPopulaBase.getProdutoDolphin();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });

            Plano plano = recuperaPlanoEntregaFake(produtoDolphin);

            int max_randomIDS = 100;
            int max_dias_anteriores = 50;

            return Arrays.asList(new String[NUM_ENTREGAS_FAKES])
                    .stream()
                    .parallel()
                    .map(s -> {
                                String codeNome = "#" + XRandom.getRandom_().nextInt(max_randomIDS) + " - " + getNextVersion();

                                return criaEntrega(UUID.randomUUID().toString(),
                                        codeNome,
                                        XRandom.getRandomEntregaEstado(),
                                        XMaestro.XDate.addDays(XRandom.getRandom_().nextInt(max_dias_anteriores) * -1),
                                        produtoDolphin,
                                        plano);
                            }
                    )
                    .collect(Collectors.toList());
        });
    }

    public List<Entrega> getEntregas() {

        return listaLazy.getIt();
    }

    protected Entrega criaEntrega(String idEntrega,
                                  String nomeEntrega,
                                  EntregaEstado entregaEstado,
                                  Calendar dataCriacao,
                                  Produto produtoDolphin,
                                  Plano plano) {
//        logger.info("::criaEntrega::>" + XMaestro.XDate.formatDate(dataCriacao));

        Entrega entrega = new Entrega();

        // APP
        ProdutoAppVersao produtoAppVersao = new ProdutoAppVersao()
                .ordemExecucao(1L)
                .produto(produtoDolphin)
                .versaoProduto("17.4.1")
                .artefatos(XList.toList(
                        new Artefato().setFileUri("dolphin-desktop/bootstrap/bootstrap-1.0.exe")
                                .setNome("bootstrap").setExtensao("exe").setVersao("1.0"),
                        new Artefato().setFileUri("dolphin-desktop/packmain/packmain-1.0.bpl")
                                .setNome("packmain").setExtensao("bpl").setVersao("1.0"),
                        new Artefato().setFileUri("dolphin-desktop/packlib/packlib-1.0.bpl")
                                .setNome("packlib").setExtensao("bpl").setVersao("1.0")
                ));

        ConteudoApp conteudoApp = new ConteudoApp()
                .versao(produtoAppVersao);

        // BD
        ProdutoDbRevision produtoDbRevision = new ProdutoDbRevision()
                .ordemExecucao(1L)
                .produto(produtoDolphin)
                .revisaoBanco("v6.0")
                .scripts(XList.toList("BEGIN\n" +
                        "  DOLPHIN_PACK.PADICIONAAVISODOLPHIN(1,'classexx', '" + String.valueOf(new Random().nextInt()) + "', SYSDATE, '" + nomeEntrega + "','N');\n" +
                        "  COMMIT;\n" +
                        "END;/"));
        ConteudoDb conteudoDb = new ConteudoDb()
                .revisao(produtoDbRevision);

        produtoDolphin.versoesApp(XList.toList(produtoAppVersao));
        produtoDolphin.revisoesDB(XList.toList(produtoDbRevision));

        // Entrega
        entrega.setId(idEntrega);
        entrega.codinomeEntrega(nomeEntrega)
                .estado(entregaEstado)
                .dataCriacao(dataCriacao)
                .plano(plano)
                .conteudos(XList.toList(conteudoApp, conteudoDb));

//        logger.info("::ENTREGAPOPULAFAKE::>plano");
        plano.getPlanoClassificadores()
                .stream()
                .sorted(Comparator.comparingLong(PlanoClassificador::getOrdemExecucao))
                .forEach((classificador) -> {

                    //ADD ESTAGIOS por classificador...
                    EstagioExecucaoEntrega execucaoEntrega = new EstagioExecucaoEntrega()
                            .classificador(classificador)
                            .entrega(entrega)
                            .estado(XRandom.getRandomEstagioExecucaoEstado());
                    execucaoEntrega.setId(UUID.randomUUID().toString());

                    entrega.getEstagios().add(execucaoEntrega);

                    //PARSE querie e cria os alvos...
                    List<Empresa> empresas = this
                    /*<< RUN Dynamic QUERIE <<<*/
                            .getEmpresasFromQuerie(classificador.getQueryEmpresas());

                    //cria alvo "MACRO" por empresa
                    for (Empresa empresa : empresas) {
                        EntregaAlvo alvo = new EntregaAlvo()
                                .empresa(empresa)
                                .estado(XRandom.getRandomEntregaAlvoEstado())
                                .requerAutorizacao(true)
                                .estagio(execucaoEntrega);
                        alvo.setId(UUID.randomUUID().toString());
                        execucaoEntrega.getAlvos().add(alvo);
                    }
                });

        return entrega;
    }

    protected ConcurrentHashMap<String, List<Empresa>> cachedMap = new ConcurrentHashMap<>();

    protected List<Empresa> getEmpresasFromQuerie(String querie) {
        return cachedMap.getOrDefault(querie,
                entregaRepository.getContext().createQuery(querie, Empresa.class).getResultList()
        );
    }

    protected Plano recuperaPlanoEntregaFake(Produto produtoDolphin) {

        //CONFIGURA PLANO DE ENTREGA...
        Plano plano = new Plano().produto(produtoDolphin).nome("Plano Default");
        PlanoClassificador pe_classifQA = new PlanoClassificador()
                .plano(plano)
                .nomeClassificador(TipoClassificador.QA.getDescricao())
                .tipoClassificador(TipoClassificador.QA)
                .ordemExecucao(1)
                .queryEmpresas("select e from Empresa e where 'QA' MEMBER OF e.tags");

        PlanoClassificador pe_classifTESTE = new PlanoClassificador()
                .plano(plano)
                .nomeClassificador(TipoClassificador.TESTE.getDescricao())
                .tipoClassificador(TipoClassificador.TESTE)
                .ordemExecucao(2)
                .queryEmpresas("select e from Empresa e where 'TESTE' MEMBER OF e.tags");

        PlanoClassificador pe_classifPRODUCAO = new PlanoClassificador()
                .plano(plano)
                .nomeClassificador(TipoClassificador.PRODUCAO.getDescricao())
                .tipoClassificador(TipoClassificador.PRODUCAO)
                .ordemExecucao(3)
                .queryEmpresas("select e from Empresa e where 'PRODUCAO' MEMBER OF e.tags");

        plano.classificadores(XList.toList(pe_classifQA, pe_classifTESTE, pe_classifPRODUCAO));
        produtoDolphin.getPlanos().add(plano);

        return plano;
    }
}