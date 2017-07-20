package py.com.datapar.maestro.lib.helper;

import org.reflections.ReflectionUtils;
import py.com.datapar.maestro.lib.utils.LazyInjector;

import javax.enterprise.inject.spi.CDI;
import java.util.function.Function;

/**
 * The type XMaestro.
 * Helper and Locator ChuckNorris++
 */
//todo refatorar nome dos fields, para facilitar o autocompletar (está olhando na classe e nao na instancia do static)
    //Xlista -> list, Xdate -> date
public final class XMaestro {

    /**
     * The method get retorna uma instancia da classe informada. Utilizando o CDI container para injetar possiveis anotações cdi.
     *
     * @param <T>    cast generico para o mesmo tipo de classe informada
     * @param tClass Classexx.class
     * @return the retorna nova instancia de Classexx, injetada pelo CDI
     */
    public static <T> T get(Class<T> tClass) {
        return CDI.current().select(tClass).get();
    }

    /**
     * The method getLazy retorna uma instancia LazyInjector ... bah
     *
     * @param <T>    the type parameter
     * @param tClass the t class
     * @return the lazy injector
     */
    public static <T> LazyInjector<T> getLazy(Class<T> tClass) {
        return new LazyInjector<>(tClass);
    }

    public static <T extends Object> Object iiff(T obj, Function<T, ?> f) {
        if (obj != null) {
            return f.apply(obj);
        }
        return null;
    }


    /**
     * The constant XList.
     */
    public static XList XList;

    /**
     * The constant XDate.
     */
    public static XDate XDate;

    /**
     * The constant XFormater.
     */
    public static XFormater XFormater;

    /**
     * The constant XDate.
     */
    public static XMap XMap;

    /**
     * The constant reflectionUtils.
     * https://github.com/ronmamo/reflections
     */
    public static ReflectionUtils reflectionUtils;

    /**
     * The constant XJson.
     */
    public static XJson XJson;

    /**
     * The constant XJson.
     */
    public static XString XString;
}

