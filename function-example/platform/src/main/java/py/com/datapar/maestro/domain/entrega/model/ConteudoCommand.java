package py.com.datapar.maestro.domain.entrega.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@JsonTypeName("conteudo_command")
@Entity
public class ConteudoCommand extends Conteudo {

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @ElementCollection
    private List<String> comandos;


    public List<String> getComandos() {
        return this.comandos;
    }

    public void setComandos(List<String> comandos) {
        this.comandos = comandos;
    }

    public ConteudoCommand comandos(List<String> comandos) {
        this.comandos = comandos;
        return this;
    }

}
