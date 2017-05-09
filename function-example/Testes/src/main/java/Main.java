
import java.util.function.Function;

/*w w  w  . ja  v a 2  s. co m*/
public class Main {

    public static void main(String[] args) {
        Function<Integer, Integer> id = Function.identity();

        System.out.println(id.apply(5));

    }
}
