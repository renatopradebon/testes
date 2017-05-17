import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
/*  w ww.  ja  va 2 s .c o  m*/
public class Main2 {
   public static void main(String[] args) {
      Function<Double, Double> square = number -> number * number;
      Function<Double, Double> half = number -> number * 2;

      List<Double> numbers = Arrays.asList(10D, 4D, 12D);
      // you can use identity to not modify them
      System.out.println(mapIt(numbers, Function.<Double>identity()));
      System.out.println(square);
      
      
   }

   private static List<Double> mapIt(List<Double> numbers, Function<Double, Double> fx) {
      List<Double> result = new ArrayList<>();

      for (Double number : numbers) {
         result.add(fx.apply(number));
      }

      return result;
   }
}