package py.com.datapar.maestro.domain.produto.service;

import py.com.datapar.maestro.domain.cliente.model.ConfigSchemaAppRevision;
import py.com.datapar.maestro.domain.cliente.model.ConfigSchemaDbRevision;
import py.com.datapar.maestro.domain.generico.Repository;
import py.com.datapar.maestro.domain.generico.ServiceImpl;
import py.com.datapar.maestro.domain.produto.model.Produto;
import py.com.datapar.maestro.domain.produto.model.ProdutoAppVersao;
import py.com.datapar.maestro.domain.produto.model.ProdutoDbRevision;
import py.com.datapar.maestro.domain.produto.repository.PlanoRepository;
import py.com.datapar.maestro.domain.produto.repository.ProdutoAppVersaoRepository;
import py.com.datapar.maestro.domain.produto.repository.ProdutoDbRevisionRepository;
import py.com.datapar.maestro.domain.produto.repository.ProdutoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;


@Named
@RequestScoped
public class ProdutoService extends ServiceImpl<ProdutoRepository, Produto> {

    @Inject
    private ProdutoRepository repository;

    @Inject
    private PlanoRepository planoRepository;

    @Inject
    private ProdutoAppVersaoRepository produtoAppVersaoRepository;

    @Inject
    private ProdutoDbRevisionRepository produtoDbRevisionRepository;

    @Override
    public Repository<Produto> getRepository() {
        return repository;
    }

    public ProdutoService() {
        //todo add rules
    }

    public List<ProdutoAppVersao> getAppRevisionAvaliable(Produto produto) {
        String produtoId = produto.getId();

        // todo Adicionar validação para não trazer versões já utilizadas em entregas em andamento. Só existe validação para entrega concluída.
        return produtoAppVersaoRepository
                .getQuery()
                .where(obj -> obj.getProduto().getId().equals(produtoId))
                .where((obj, source) -> source
                        .stream(ConfigSchemaAppRevision.class)
                        .where(configSchemaAppRevision -> configSchemaAppRevision.getProdutoAppVersao().getId().equals(obj.getId()))
                        .count() == 0
                )
                .sortedDescendingBy(val -> val.getOrdemExecucao())
                .toList();
    }

    public List<ProdutoDbRevision> getDbRevisionAvaliable(Produto produto, ProdutoAppVersao produtoAppVersao) {
        String versaoMinimaPermitida = produtoAppVersao != null ? produtoAppVersao.getRevisaoMinimaDB() : "";
        String produtoId = produto.getId();

        // todo Adicionar validação para não trazer versões já utilizadas em entregas em andamento. Só existe validação para entrega concluída.
        return produtoDbRevisionRepository
                .getQuery()
                .where(obj -> obj.getProduto().getId().equals(produtoId))
                .where((obj, source) -> source
                        .stream(ConfigSchemaDbRevision.class)
                        .where(configSchemaDbRevision -> configSchemaDbRevision.getProdutoDbRevision().getId().equals(obj.getId()))
                        .count() == 0
                )
                .sortedDescendingBy(val -> val.getOrdemExecucao())
                // todo adicionar validação para versão minima
//                .where((obj, source) -> XMaestro.XString.versionCompare(obj.getRevisaoBanco(), versaoMinimaPermitida) > 0)
                .collect(Collectors.toList());
    }

    public List<ProdutoDbRevision> getDbRevisionAvaliable(Produto produto) {
        return getDbRevisionAvaliable(produto, null);
    }

    public long lastOrdemDbRevision(Produto produto) {
        return produtoDbRevisionRepository
                .getQuery()
                .where(obj -> obj.getProduto().equals(produto))
                .max(val -> val.getOrdemExecucao());
    }

    public long lastOrdemAppVersao(Produto produto) {
        return produtoAppVersaoRepository
                .getQuery()
                .where(obj -> obj.getProduto().equals(produto))
                .max(val -> val.getOrdemExecucao());
    }

}