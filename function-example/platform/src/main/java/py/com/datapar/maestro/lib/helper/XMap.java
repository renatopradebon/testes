package py.com.datapar.maestro.lib.helper;

import java.util.Map;
import java.util.function.Supplier;

public class XMap {

    public static <K, V> V getOrElse(Map map, K key, Supplier<V> supplier) {
        V value = (V) map.get(key);
        if (value == null && !map.containsKey(key)){
            map.put(key, value = supplier.get());
        }
        return value;
    }


}