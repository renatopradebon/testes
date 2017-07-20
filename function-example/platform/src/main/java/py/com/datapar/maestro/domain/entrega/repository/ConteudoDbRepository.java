package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.ConteudoDb;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ConteudoDbRepository extends JpaRepository<ConteudoDb> {

    public ConteudoDbRepository() {
        super(ConteudoDb.class);
    }
}