package py.com.datapar.maestro.lib.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;

public final class XJson {

    protected static ObjectMapper configMapper(ObjectMapper mapper) {
        //config json mapper
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        //mapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        return mapper;
    }

    private static String writeResult(ObjectMapper mapper, Object object) {
        try {

            return mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Erro ao mapear o objeto:: " + object.toString() + " " + e.getMessage();
        }
    }

    public static String toJson(Object object) {
        return writeResult(configMapper(new ObjectMapper()), object);
    }

    public static String asYaml(Object object) {
        return writeResult(configMapper(new YAMLMapper()), object);
    }

    public static <T> T fromJson(String json, TypeReference typeReference) throws IOException {
        return configMapper(new ObjectMapper()).readValue(json, typeReference);
    }

    public static <T> T fromJson(String json, Class<T> tClass) throws IOException {
        return configMapper(new ObjectMapper()).readValue(json, tClass);
    }
}
