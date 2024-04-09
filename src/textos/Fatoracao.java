package textos;

import java.util.ArrayList;
import java.util.List;

public class Fatoracao {

    public static List<Integer> fatoresPrimos(int number) {
        List<Integer> fatores = new ArrayList<>();
        // Divide por 2 para remover todos os fatores 2
        while (number % 2 == 0) {
            fatores.add(2);
            number /= 2;
        }
        // Fatoração para números ímpares
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                fatores.add(i);
                number /= i;
            }
        }
        // Se o número restante for um primo maior que 2
        if (number > 2) {
            fatores.add(number);
        }
        return fatores;
    }
}