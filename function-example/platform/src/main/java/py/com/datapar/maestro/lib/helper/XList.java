package py.com.datapar.maestro.lib.helper;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XList {

    public static <T> List<T> toList(T... arrayofT){
        return Arrays.asList(arrayofT);
    }

    public static <T> List<T> createIfNull(List<T> listof){
        if (listof == null)
            return new ArrayList<>();
        return listof;
    }


}
