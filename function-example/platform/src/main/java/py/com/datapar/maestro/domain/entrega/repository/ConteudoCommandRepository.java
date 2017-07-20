package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.ConteudoCommand;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ConteudoCommandRepository extends JpaRepository<ConteudoCommand> {

    public ConteudoCommandRepository() {
        super(ConteudoCommand.class);
    }
}