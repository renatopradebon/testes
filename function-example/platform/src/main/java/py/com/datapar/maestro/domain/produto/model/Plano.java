package py.com.datapar.maestro.domain.produto.model;

import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Plano extends EntityWithIdentity {

    @ManyToOne(optional = true, targetEntity = Produto.class)
    private Produto produto;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = PlanoClassificador.class, orphanRemoval = false, mappedBy = "plano")
    @OrderBy("ordemExecucao")
    private List<PlanoClassificador> planoClassificadores;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    @NotNull(message = "El nombre es obligatorio!")
    @Size(max = 255)
    private String nome;

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Plano produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public List<PlanoClassificador> getPlanoClassificadores() {
        return this.planoClassificadores;
    }

    public void setPlanoClassificadores(List<PlanoClassificador> classificadores) {
        this.planoClassificadores = classificadores;
    }

    public Plano classificadores(List<PlanoClassificador> classificadores) {
        this.planoClassificadores = classificadores;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Plano nome(String nome) {
        this.nome = nome;
        return this;
    }

}
