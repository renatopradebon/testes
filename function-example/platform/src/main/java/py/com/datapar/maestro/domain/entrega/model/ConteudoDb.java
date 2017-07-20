package py.com.datapar.maestro.domain.entrega.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import py.com.datapar.maestro.domain.produto.model.ProdutoDbRevision;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@JsonTypeName("conteudo_db")
@Entity
public class ConteudoDb extends Conteudo {

    @OneToOne(optional = true, orphanRemoval = false, targetEntity = ProdutoDbRevision.class)
    private ProdutoDbRevision revisao;


    public ProdutoDbRevision getRevisao() {
        return this.revisao;
    }

    public void setRevisao(ProdutoDbRevision revisao) {
        this.revisao = revisao;
    }

    public ConteudoDb revisao(ProdutoDbRevision revisao) {
        this.revisao = revisao;
        return this;
    }

}
