package py.com.datapar.maestro.domain.entrega.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConteudoApp.class, name = "conteudo_app"),
        @JsonSubTypes.Type(value = ConteudoDb.class, name = "conteudo_db"),
        @JsonSubTypes.Type(value = ConteudoCommand.class, name = "conteudo_command")
})
public abstract class Conteudo extends EntityWithIdentity {

}
