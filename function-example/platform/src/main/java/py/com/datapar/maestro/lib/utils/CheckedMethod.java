package py.com.datapar.maestro.lib.utils;

@FunctionalInterface
public interface CheckedMethod<T> {
    T get() throws Exception;
}