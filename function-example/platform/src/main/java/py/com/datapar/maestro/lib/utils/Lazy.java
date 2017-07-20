package py.com.datapar.maestro.lib.utils;

public class Lazy<T> extends LazyInitializer<T> {

    private CheckedMethod<T> supplier;

    protected Lazy() {
    }

    public Lazy(CheckedMethod<T> supplier) {
        this.supplier = supplier;
    }

    public void setSupplier(CheckedMethod<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    protected T initialize() throws Exception {
        return supplier.get();
    }
}

interface ConcurrentInitializer<T> {
    T get() throws Exception;
}

abstract class LazyInitializer<T> implements ConcurrentInitializer<T> {
    private volatile T object;

    public LazyInitializer() {
    }

    public boolean initialized() {
        return this.object != null;
    }

    public void reset() {
        this.object = null;
    }

    public T get() throws Exception {
        T result = this.object;
        if(result == null) {
            synchronized(this) {
                result = this.object;
                if(result == null) {
                    this.object = result = this.initialize();
                }
            }
        }

        return result;
    }

    public T getIt(){
        try {
            return get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract T initialize() throws Exception;
}

