package py.com.datapar.maestro.domain.produto.repository;

import py.com.datapar.maestro.domain.generico.JpaRepository;
import py.com.datapar.maestro.domain.produto.model.ProdutoDbRevision;

public class ProdutoDbRevisionRepository extends JpaRepository<ProdutoDbRevision> {

    public ProdutoDbRevisionRepository() {
        super(ProdutoDbRevision.class);
    }

}
