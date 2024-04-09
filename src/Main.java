import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            String text = Reader.readText("src/textos/20201-teste2.txt");
            Set<String> repeatingWords = Kasiski.getRepeatingWords(text);
            Map<String, Set<Integer>> distances = Kasiski.getDistances(text, repeatingWords);

            repeatingWords.forEach(word -> {
                System.out.println("Palavra: " + word + " | Distâncias: " + distances.get(word));
            });
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}