package py.com.datapar.maestro.domain.generico;

import com.fasterxml.jackson.core.type.TypeReference;
import py.com.datapar.maestro.lib.helper.XMaestro;

import javax.persistence.AttributeConverter;
import java.io.IOException;


public abstract class JsonAttributeConverter<T extends Object> implements AttributeConverter<T, String> {

    /**
     * Gets strong type reference gambi.
     * Java generic runtime type é uma gambi pura.. não sendo possivel acessar o tipo < T > para instanciar objetos
     * @return the strong typed type reference gambi
     */
    protected abstract TypeReference<T> getStrongTypeReferenceGambi();

    @Override
    public String convertToDatabaseColumn(T data) {
        if (data == null)
            return null;
        return XMaestro.XJson.toJson(data);
    }

    @Override
    public T convertToEntityAttribute(String data) {
        if (data == null || data.isEmpty())
            return null;
        try {
            TypeReference<T> typeRef = getStrongTypeReferenceGambi();
            if (typeRef == null) {
                //tenta resolver por reflexao... 99% fail
                typeRef = new TypeReference<T>() {};
            }
            return XMaestro.XJson.fromJson(data, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}