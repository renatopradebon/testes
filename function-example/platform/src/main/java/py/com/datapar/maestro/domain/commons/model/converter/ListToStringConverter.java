package py.com.datapar.maestro.domain.commons.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import py.com.datapar.maestro.domain.generico.JsonAttributeConverter;

import javax.persistence.Converter;
import java.util.ArrayList;

//USAR nas entidades, no attributo List<String> lista, e o mesmo será persistico em uma coluna string com conteudo JSON
//@Column(name = "Tags")
//@Convert(converter = ListToStringConverter.class)
//List<String> listValue;

@Converter
public class ListToStringConverter extends JsonAttributeConverter<ArrayList<String>> {

    @Override
    protected TypeReference<ArrayList<String>> getStrongTypeReferenceGambi() {

        //necessário retornar um tipo anonimo para ter acesso ao tipo "vestigial"
        return new TypeReference<ArrayList<String>>() {};
    }
}
