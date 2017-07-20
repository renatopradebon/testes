package py.com.datapar.maestro.domain.produto.repository;

import py.com.datapar.maestro.domain.generico.JpaRepository;
import py.com.datapar.maestro.domain.produto.model.Produto;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;

@Default
@RequestScoped
public class ProdutoRepository extends JpaRepository<Produto> {
    
    public ProdutoRepository() {
        super(Produto.class);
    }
    
}
