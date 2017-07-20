package py.com.datapar.maestro.domain.entrega.service.test.popula;

import org.slf4j.Logger;
import py.com.datapar.maestro.domain.cliente.model.*;
import py.com.datapar.maestro.domain.cliente.service.ClienteService;
import py.com.datapar.maestro.domain.commons.model.vo.BancoUtilizado;
import py.com.datapar.maestro.domain.commons.model.vo.ProtudoTipo;
import py.com.datapar.maestro.domain.commons.model.vo.TipoClassificador;
import py.com.datapar.maestro.domain.generico.ServiceResult;
import py.com.datapar.maestro.domain.produto.model.*;
import py.com.datapar.maestro.domain.produto.service.ProdutoService;
import py.com.datapar.maestro.lib.helper.XFormater;
import py.com.datapar.maestro.lib.helper.XList;
import py.com.datapar.maestro.lib.utils.ListBuilder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import static py.com.datapar.maestro.domain.resources.DomainConts.CLIENTE_SERVIDOR_HOST;

@RequestScoped
public class InitPopulaBase {

    // Service injetado atraves da CDI
    @Inject
    ClienteService clienteService;
    @Inject
    ProdutoService produtoService;

    @Inject
    Logger logger;

    public Produto getProdutoDolphin() {
        return produtoDolphin;
    }

    Produto produtoDolphin;

    //DATAPAR... maestro
    public static final ServidorInfo DATAPAR_SERVIDOR_01 = new ServidorInfo("172.27.1.40", 8180, 9990, "172.27.13.19"/*DB*/, 1521);

    //    Pindo, 172.27 .13 .19:9992, admin, ervamate
    public static final ServidorInfo PINDO_SERVIDOR_01 = new ServidorInfo("172.27.1.41", 8180, 9990, "172.27.1.41"/*DB*/, 1521);
    //    Pindo - filial1, 172.27 .13 .19:9989, admin, ervamate
    public static final ServidorInfo PINDO_SERVIDOR_02 = new ServidorInfo("172.27.1.42", 8180, 9990, "172.27.1.42"/*DB*/, 1521);

    //    COFCO, 172.27 .13 .19:9994, admin, ervamate
    public static final ServidorInfo COFCO_SERVIDOR_01 = new ServidorInfo("172.27.1.70", 8180, 9090, "172.27.13.19"/*DB*/, 1521);

    //    Catalina, 172.27 .13 .19:9995, admin, ervamate
    public static final ServidorInfo CATALINA_SERVIDOR_01 = new ServidorInfo("172.27.1.71", 8180, 9990, "172.27.13.19"/*DB*/, 1521);
    //    Catalina - filial1, 172.27 .13 .19:9999, admin, ervamate
    public static final ServidorInfo CATALINA_SERVIDOR_02 = new ServidorInfo("172.27.1.72", 8180, 9990, "172.27.13.19"/*DB*/, 1521);

    //docker
    //    Colonial, 172.27 .13 .19:9993, admin, ervamate
    public static final ServidorInfo COLONIAL_SERVIDOR_01 = new ServidorInfo("172.27.13.19", 8090, 9990, "172.17.0.3"/*DB*/, 1521);
    //    Colonial - filial1, 172.27 .13 .19:9986, admin, ervamate
    public static final ServidorInfo COLONIAL_SERVIDOR_02 = new ServidorInfo("172.27.13.19", 8091, 9991, "172.17.0.3"/*DB*/, 1521);
    //    Colonial - filial2, 172.27 .13 .19:9997, admin, ervamate
    public static final ServidorInfo COLONIAL_SERVIDOR_03 = new ServidorInfo("172.27.13.19", 8092, 9992, "172.17.0.3"/*DB*/, 1521);
    //    Colonial - filial3, 172.27 .13 .19:9998, admin, ervamate
    public static final ServidorInfo COLONIAL_SERVIDOR_04 = new ServidorInfo("172.27.13.19", 8093, 9993, "172.17.0.3"/*DB*/, 1521);


    public static final String PINDO = "PINDO";
    public static final String COLONIAL = "COLONIAL";
    public static final String COFCO = "BAELPA";
    public static final String CATALINA = "CATALINA";


    public class ServidorPack {
        Servidor servidorApp = new Servidor();
        ServicoApp servicoApp = new ServicoApp();
        Servidor servidorDb = new Servidor();
        ServicoDb servicoDb = new ServicoDb();
    }

    public static class ServidorInfo {
        String appHost;
        Integer appPort;
        Integer appConsPort;
        String dbHost;
        Integer dbPort;

        public ServidorInfo(String appHost, Integer appPort, Integer appConsPort, String dbHost, Integer dbPort) {
            this.appHost = appHost;
            this.appPort = appPort;
            this.appConsPort = appConsPort;
            this.dbHost = dbHost;
            this.dbPort = dbPort;
        }
    }


    public ConfigSchema configSchema(String schemaName, Filial filial, ServicoApp servicoAPP, ServicoDb servicoDb) {
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        // Schema Matriz
        ConfigSchema configSchema = new ConfigSchema()
                .schemaName(schemaName)
                .schemaTestEnabled(Boolean.FALSE)
                .servicoAPP(servicoAPP)
                .produto(produtoDolphin)
                .filial(filial)
                .servicoDB(servicoDb);

        servicoAPP.configSchemas(new ListBuilder<ConfigSchema>()
                .add(configSchema)
                .toList());
        servicoDb.configSchemas(new ListBuilder<ConfigSchema>()
                .add(configSchema)
                .toList());
        filial.configSchemas(new ListBuilder<ConfigSchema>()
                .add(configSchema)
                .toList());

        return configSchema;

    }

    public ServidorPack criaServidor(Cliente cliente, ServidorInfo servidorInfo, String dbLogin, String dbPass, ServidorPack parent) {
        /////////////SERVIDORES - 01/////////////////////////////////
        ServidorPack servidorPack = new ServidorPack();
        servidorPack.servidorApp
                .cliente(cliente)
                .contato("Contato - App")
                .observacao("Servidor App obs")
                .hostIP(servidorInfo.appHost)
                .servicos(new ListBuilder<Servico>()
                        //ADD SERVICO APP*************
                        .add(servidorPack.servicoApp
                                .apptoken("token-App-" + cliente.getNome())
                                .hostPort(servidorInfo.appPort)
                                .hostConsolePort(servidorInfo.appConsPort)
                                .servidor(servidorPack.servidorApp)
                                .parent(parent == null ? null : parent.servicoApp)
                                .maestroAcessKey("chave-acesso-" + cliente.getNome()))
                        .toList());
        // Servidor/Servidor2*************
        servidorPack.servidorDb
                .cliente(cliente)
                .contato("Contato - oracle")
                .observacao("Servidor DB obs")
                .hostIP(servidorInfo.dbHost)
                .servicos(new ListBuilder<Servico>()
                        //ADD SERVICO DB*************
                        .add(servidorPack.servicoDb
                                .userdbaLogin(dbLogin)
                                .userdbaPassword(dbPass)
                                .connectionURL(String.format("jdbc:oracle:thin:@%1s:XE", XFormater.formatAsVariable(CLIENTE_SERVIDOR_HOST) + ":" + servidorInfo.dbPort.toString()))
                                .bancoUtilizado(BancoUtilizado.ORACLE)
                                .servidor(servidorPack.servidorDb)
                                .parent(parent == null ? null : parent.servicoDb)
                                .maestroAcessKey("chave-acesso-db-" + cliente.getNome()))
                        .toList());

        cliente.getServidores().add(servidorPack.servidorApp);
        cliente.getServidores().add(servidorPack.servidorDb);

        return servidorPack;
    }


    public void initPopula() throws Exception {

        populaProduto();

        Cliente pindo = initPopulaClientePindo();
        Cliente colonial = initPopulaClienteColonial();
        Cliente catalina = initPopulaClienteCatalina();
        Cliente cofco = initPopulaClienteCofco();

        //cross references
        produtoDolphin.clientes(XList.toList(pindo, colonial, catalina, cofco));
        produtoService.grava(produtoDolphin);
    }

    protected Plano recuperaPlanoEntregaFake() {

        //CONFIGURA PLANO DE ENTREGA...
        Plano plano = new Plano()
                .produto(produtoDolphin)
                .nome("Plano Default");

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

    public void populaProduto() throws Exception {
        logger.info("populando Produto...");
        ///////////////////////////////////////////////////PRODUTO//////////////////////////////////////////////////////
        produtoDolphin = new Produto()
                .nome("DOLPHIN")
                .bancoUtilizado(BancoUtilizado.ORACLE)
                .tipoProduto(ProtudoTipo.DESKTOP)
                .pathImagem("products/dolphin.png");

        //CONFIGURA PLANO DE ENTREGA...
        recuperaPlanoEntregaFake();

        //todo adicionar versões do App e do Banco (ProdutoAppVersao) (ProdutoDbRevision)
        Artefato artefato1 = new Artefato()
                .setExtensao("pdc")
                .setFileUri("artifactory/script_banco")
                .setNome("script_banco")
                .setVersao("17.1.5")
                .setSha1("sha1");

        Artefato artefato2 = new Artefato()
                .setExtensao("pdc")
                .setFileUri("scrartifactory/script_banco")
                .setNome("script_banco2")
                .setVersao("17.1.5")
                .setSha1("sha2");

        Artefato artefato3 = new Artefato()
                .setExtensao("pdc")
                .setFileUri("artifactory/script_banco")
                .setNome("script_banco3")
                .setVersao("17.1.6")
                .setSha1("sha3");

        Artefato artefato4 = new Artefato()
                .setExtensao("pdc")
                .setFileUri("scrartifactory/script_banco")
                .setNome("script_banco4")
                .setVersao("17.1.6")
                .setSha1("sha4");

        ProdutoAppVersao produtoAppVersao_1715 = new ProdutoAppVersao()
                .ordemExecucao(1L)
                .versaoProduto("17.1.5")
                .revisaoMinimaDB("1.5")
                .artefatos(new ListBuilder<Artefato>()
                        .add(artefato1)
                        .add(artefato2)
                        .toList())
                .produto(produtoDolphin);

        ProdutoDbRevision produtoDbRevision_1715 = new ProdutoDbRevision()
                .ordemExecucao(1L)
                .revisaoBanco("1.5")
                .scripts(new ListBuilder<String>()
                        .add("alter table teste add column testColumn;")
                        .add("alter table teste2 add column testeColumn2")
                        .toList())
                .produto(produtoDolphin);

        ProdutoAppVersao produtoAppVersao_1716 = new ProdutoAppVersao()
                .ordemExecucao(2L)
                .versaoProduto("17.1.6")
                .revisaoMinimaDB("1.6")
                .artefatos(new ListBuilder<Artefato>()
                        .add(artefato3)
                        .add(artefato4)
                        .toList())
                .produto(produtoDolphin);

        ProdutoDbRevision produtoDbRevision_1716 = new ProdutoDbRevision()
                .ordemExecucao(2L)
                .revisaoBanco("1.6")
                .scripts(new ListBuilder<String>()
                        .add("alter table teste3 add column testColumn3;")
                        .add("alter table teste4 add column testeColumn4")
                        .toList())
                .produto(produtoDolphin);

        // artefatos - referência cruzada
        artefato1.setVersoes(new ListBuilder<ProdutoAppVersao>()
                .add(produtoAppVersao_1715)
                .toList());
        artefato2.setVersoes(new ListBuilder<ProdutoAppVersao>()
                .add(produtoAppVersao_1715)
                .toList());
        artefato3.setVersoes(new ListBuilder<ProdutoAppVersao>()
                .add(produtoAppVersao_1716)
                .toList());
        artefato4.setVersoes(new ListBuilder<ProdutoAppVersao>()
                .add(produtoAppVersao_1716)
                .toList());

        produtoDolphin.versoesApp(new ListBuilder<ProdutoAppVersao>()
                .add(produtoAppVersao_1715)
                .add(produtoAppVersao_1716)
                .toList());
        produtoDolphin.revisoesDB(new ListBuilder<ProdutoDbRevision>()
                .add(produtoDbRevision_1715)
                .add(produtoDbRevision_1716)
                .toList());

        //todo Exibir errors visualmente
        ServiceResult<Produto> serviceResult = produtoService.grava(produtoDolphin);
        produtoDolphin = serviceResult.getEntity();
        if (serviceResult.hasErrors())
            logger.error("ERRO AO GRAVAR PRODUTO:: " + serviceResult.getErrorList().toString());
    }

    private Cliente initPopulaClientePindo() throws IllegalAccessException, InstantiationException {
        logger.info("populando initPopulaClientePindo...");

        Empresa empresa;
        Cliente cliente = clienteService.criaNova();
        // Cliente
        cliente
                .nome(PINDO)
                .codigo(101)
                //EMPRESAS DO CLIENTE
                .empresas(XList.toList(
                        empresa = new Empresa()
                                .nome("Empresa " + PINDO)
                                .tags(XList.toList("TESTE"))
                                .cliente(cliente)
                        )
                );

        // Filiais
        Filial filial01 = new Filial().nome("Matriz").empresa(empresa);
        Filial filial02 = new Filial().nome("Filial 1").empresa(empresa);
        Filial filial03 = new Filial().nome("Filial 2").empresa(empresa);
        // Filial
        empresa.filiais(XList.toList(filial01, filial02, filial03));

        //SERVIDORES
        ServidorPack servidorPack_01 = criaServidor(cliente, PINDO_SERVIDOR_01, "PINDO", "SUPERPINDO", null);
        ServidorPack servidorPack_02 = criaServidor(cliente, PINDO_SERVIDOR_02, "PINDO", "SUPERPINDO", servidorPack_01);
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        configSchema("PINDO", filial01, servidorPack_01.servicoApp, servidorPack_01.servicoDb);
        configSchema("PINDO", filial02, servidorPack_02.servicoApp, servidorPack_02.servicoDb);
        configSchema("PINDO", filial03, servidorPack_02.servicoApp, servidorPack_02.servicoDb);//mesmo servidor...
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        //todo Exibir errors visualmente
        ServiceResult<Cliente> serviceResult = clienteService.grava(cliente);
        cliente = serviceResult.getEntity();
        if (serviceResult.hasErrors())
            logger.error("ERRO AO GRAVAR CLIENTE:: " + serviceResult.getErrorList().toString());

        return cliente;
    }

    // Catalina
    public Cliente initPopulaClienteCatalina() throws IllegalAccessException, InstantiationException {
        logger.info("populando initPopulaClienteCatalina...");

        Empresa empresa = new Empresa();
        Cliente cliente = clienteService.criaNova();
        // Cliente
        cliente
                .nome(CATALINA)
                .codigo(22)
                //EMPRESAS DO CLIENTE
                .empresas(
                        new ListBuilder<Empresa>()
                                //ADD EMPRESA CLIENTE
                                .add(empresa
                                        .nome("Empresa " + CATALINA)
                                        .tags(XList.toList("PRODUCAO"))
                                        .cliente(cliente)
                                )
                                .toList());

        // Filial/Matriz
        Filial filial01 = new Filial().nome("Matriz").empresa(empresa);
        Filial filial02 = new Filial().nome("Filial 1'").empresa(empresa);
        // Filial
        empresa.filiais(XList.toList(filial01, filial02));

        //SERVIDORES
        ServidorPack servidorPack_M = criaServidor(cliente, CATALINA_SERVIDOR_01, "ASSCSA", "SUPERASSCSA", null);
        ServidorPack servidorPack_F = criaServidor(cliente, CATALINA_SERVIDOR_02, "ASSCSA", "SUPERASSCSA", servidorPack_M);
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        configSchema("ASSCSA", filial01, servidorPack_M.servicoApp, servidorPack_M.servicoDb);
        configSchema("ASSCSA", filial02, servidorPack_F.servicoApp, servidorPack_F.servicoDb);
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        //todo Exibir errors visualmente
        ServiceResult<Cliente> serviceResult = clienteService.grava(cliente);
        cliente = serviceResult.getEntity();
        if (serviceResult.hasErrors())
            logger.error("ERRO AO GRAVAR CLIENTE:: " + serviceResult.getErrorList().toString());

        return cliente;
    }

    // Colonial
    public Cliente initPopulaClienteColonial() throws IllegalAccessException, InstantiationException {
        logger.info("populando initPopulaClienteColonial...");

        Empresa empresa = new Empresa();
        Cliente cliente = clienteService.criaNova();
        // Cliente
        cliente
                .nome(COLONIAL)
                .codigo(313)
                .empresas(
                        new ListBuilder<Empresa>()
                                //ADD EMPRESA CLIENTE
                                .add(empresa
                                        .nome("Empresa " + COLONIAL)
                                        .tags(XList.toList("PRODUCAO"))
                                        .cliente(cliente)
                                )
                                .toList());

        // Filial/Matriz
        Filial filial01 = new Filial().nome("Matriz").empresa(empresa);
        Filial filial02 = new Filial().nome("Filial 1").empresa(empresa);
        Filial filial03 = new Filial().nome("Filial 2").empresa(empresa);
        Filial filial04 = new Filial().nome("Filial 4").empresa(empresa);
        // Filial
        empresa.filiais(XList.toList(filial01, filial02, filial03, filial04));

        //SERVIDORES
        ServidorPack servidorPack_01 = criaServidor(cliente, COLONIAL_SERVIDOR_01, "COLONIAL", "SUPERCOLONIAL", null);
        ServidorPack servidorPack_02 = criaServidor(cliente, COLONIAL_SERVIDOR_02, "COLONIAL", "SUPERCOLONIAL", servidorPack_01);
        ServidorPack servidorPack_03 = criaServidor(cliente, COLONIAL_SERVIDOR_03, "COLONIAL", "SUPERCOLONIAL", servidorPack_01);
        ServidorPack servidorPack_04 = criaServidor(cliente, COLONIAL_SERVIDOR_04, "COLONIAL", "SUPERCOLONIAL", servidorPack_01);
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        configSchema("COLONIAL", filial01, servidorPack_01.servicoApp, servidorPack_01.servicoDb);
        configSchema("COLONIAL", filial02, servidorPack_02.servicoApp, servidorPack_02.servicoDb);
        configSchema("COLONIAL", filial03, servidorPack_03.servicoApp, servidorPack_03.servicoDb);
        configSchema("COLONIAL", filial04, servidorPack_04.servicoApp, servidorPack_04.servicoDb);
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        //todo Exibir errors visualmente
        ServiceResult<Cliente> serviceResult = clienteService.grava(cliente);
        cliente = serviceResult.getEntity();
        if (serviceResult.hasErrors())
            logger.error("ERRO AO GRAVAR CLIENTE:: " + serviceResult.getErrorList().toString());

        return cliente;
    }

    // cofco
    public Cliente initPopulaClienteCofco() throws IllegalAccessException, InstantiationException {
        logger.info("populando initPopulaClienteCofco...");

        Empresa empresa = new Empresa();
        Cliente cliente = clienteService.criaNova();
        // Cliente
        cliente
                .nome(COFCO)
                .codigo(404)
                //EMPRESAS DO CLIENTE
                .empresas(
                        new ListBuilder<Empresa>()
                                //ADD EMPRESA CLIENTE
                                .add(empresa
                                        .nome("Empresa " + COFCO)
                                        .tags(XList.toList("QA"))
                                        .cliente(cliente)
                                )
                                .toList());

        // Filial/Matriz
        Filial filial01 = new Filial().nome("Matriz").empresa(empresa);
        Filial filial02 = new Filial().nome("Filial 1").empresa(empresa);
        // Filial
        empresa.filiais(XList.toList(filial01, filial02));

        //SERVIDORES
        ServidorPack servidorPack_01 = criaServidor(cliente, COFCO_SERVIDOR_01, "BAELPA", "SUPERBAELPA", null);
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        //MESMO SERVIDOR 2 FILIAIS
        configSchema("BAELPA", filial01, servidorPack_01.servicoApp, servidorPack_01.servicoDb);
        configSchema("BAELPA", filial02, servidorPack_01.servicoApp, servidorPack_01.servicoDb);
        ///////////////////////////////////////////////////SCHEMAS//////////////////////////////////////////////////////
        //todo Exibir errors visualmente
        ServiceResult<Cliente> serviceResult = clienteService.grava(cliente);
        cliente = serviceResult.getEntity();
        if (serviceResult.hasErrors())
            logger.error("ERRO AO GRAVAR CLIENTE:: " + serviceResult.getErrorList().toString());

        return cliente;
    }
}
