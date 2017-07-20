package py.com.datapar.maestro.lib.utils;


import javax.enterprise.inject.spi.CDI;


/**
 * The type Lazy injector.
 * Inject ChuckNorris++
 * @param <T> the type parameter
 */
public class LazyInjector<T extends Object> extends Lazy<T> {

    public LazyInjector(Class<T> tClass){
        setSupplier( () -> CDI.current().select( tClass ).get() );
    }

}