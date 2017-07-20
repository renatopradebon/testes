package py.com.datapar.maestro.domain.entrega.repository;

import py.com.datapar.maestro.domain.entrega.model.Conteudo;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ConteudoRepository extends JpaRepository<Conteudo> {

    public ConteudoRepository() {
        super(Conteudo.class);
    }
}