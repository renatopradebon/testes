package py.com.datapar.maestro.domain.produto.repository;

import py.com.datapar.maestro.domain.generico.JpaRepository;
import py.com.datapar.maestro.domain.produto.model.ProdutoAppVersao;

public class ProdutoAppVersaoRepository extends JpaRepository<ProdutoAppVersao> {

    public ProdutoAppVersaoRepository() {
        super(ProdutoAppVersao.class);
    }

}
