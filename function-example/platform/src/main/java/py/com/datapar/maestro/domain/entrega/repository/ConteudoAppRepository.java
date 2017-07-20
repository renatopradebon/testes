package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.ConteudoApp;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ConteudoAppRepository extends JpaRepository<ConteudoApp> {

    public ConteudoAppRepository() {
        super(ConteudoApp.class);
    }
}