import java.util.ArrayList;
import java.util.List;

public class Fatoracao {

    public static List<Integer> fatoresPrimos(int number) {
        List<Integer> fatores = new ArrayList<>();
        for (int i = 2; i <= number / i; i++) {
            while (number % i == 0) {
                fatores.add(i);
                number /= i;
            }
        }
        if (number > 1) {
            fatores.add(number);
        }
        return fatores;
    }
}