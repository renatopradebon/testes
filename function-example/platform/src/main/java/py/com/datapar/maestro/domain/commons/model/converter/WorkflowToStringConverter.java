package py.com.datapar.maestro.domain.commons.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import py.com.datapar.maestro.domain.commons.model.WorkflowBean;
import py.com.datapar.maestro.domain.generico.JsonAttributeConverter;

import javax.persistence.Converter;


//USAR nas entidades, no attributo List<String> lista, e o mesmo será persistico em uma coluna string com conteudo JSON
//@Column(name = "Workflow")
//@Convert(converter = WorkflowToStringConverter.class)
//WorkflowBean workflowdata;

@Converter
public class WorkflowToStringConverter extends JsonAttributeConverter<WorkflowBean> {

    @Override
    protected TypeReference<WorkflowBean> getStrongTypeReferenceGambi() {

        //necessário retornar um tipo anonimo para ter acesso ao tipo "vestigial"
        return new TypeReference<WorkflowBean>() {};
    }
}