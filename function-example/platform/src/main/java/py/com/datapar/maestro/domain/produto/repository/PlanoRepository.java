package py.com.datapar.maestro.domain.produto.repository;

import py.com.datapar.maestro.domain.generico.JpaRepository;
import py.com.datapar.maestro.domain.produto.model.Plano;

public class PlanoRepository extends JpaRepository<Plano> {

    public PlanoRepository() {
        super(Plano.class);
    }

}
