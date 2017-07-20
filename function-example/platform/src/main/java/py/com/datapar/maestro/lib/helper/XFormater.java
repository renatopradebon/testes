package py.com.datapar.maestro.lib.helper;

public class XFormater {

    public static final String VARPATTERN = "<%1s/>";

    public static String formatAsVariable(String value){
        return String.format(VARPATTERN, value);
    }

}
