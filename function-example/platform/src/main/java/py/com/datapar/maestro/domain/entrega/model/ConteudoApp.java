package py.com.datapar.maestro.domain.entrega.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonTypeName;
import py.com.datapar.maestro.domain.produto.model.ProdutoAppVersao;

@JsonTypeName("conteudo_app")
@Entity
public class ConteudoApp extends Conteudo {

    @OneToOne(optional = true, orphanRemoval = false, targetEntity = ProdutoAppVersao.class)
    private ProdutoAppVersao versao;


    public ProdutoAppVersao getVersao() {
        return this.versao;
    }

    public void setVersao(ProdutoAppVersao versao) {
        this.versao = versao;
    }

    public ConteudoApp versao(ProdutoAppVersao versao) {
        this.versao = versao;
        return this;
    }

}
