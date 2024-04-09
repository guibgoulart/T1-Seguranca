import java.util.*;
import java.util.stream.Collectors;

public class Kasiski {

    // Encontra palavras (grupos de letras) repetidas no texto cifrado.
    public static Set<String> getRepeatingWords(String textoCifrado) {
        Set<String> wordsSeen = new HashSet<>();
        Set<String> repeatingWords = new HashSet<>();

        for (int i = 0; i <= textoCifrado.length() - 3; i++) {
            String currentWord = textoCifrado.substring(i, i + 3);
            if (wordsSeen.contains(currentWord)) {
                repeatingWords.add(currentWord);
            } else {
                wordsSeen.add(currentWord);
            }
        }

        return repeatingWords;
    }

    // Calcula as distâncias entre repetições das palavras encontradas.
    public static Map<String, Set<Integer>> getDistances(String textoCifrado, Set<String> repeatingWords) {
        Map<String, Integer> lastSeenIndex = new HashMap<>();
        Map<String, Set<Integer>> distances = new HashMap<>();

        for (int i = 0; i <= textoCifrado.length() - 3; i++) {
            String currentWord = textoCifrado.substring(i, i + 3);
            if (repeatingWords.contains(currentWord)) {
                if (lastSeenIndex.containsKey(currentWord)) {
                    int distance = i - lastSeenIndex.get(currentWord);
                    distances.computeIfAbsent(currentWord, k -> new HashSet<>()).add(distance);
                }
                lastSeenIndex.put(currentWord, i);
            }
        }

        return distances;
    }

    public static int encontrarComprimentoChaveProvavel(Map<String, Set<Integer>> distancias) {
        Map<Integer, Integer> contagemFatores = new HashMap<>();

        // Fatora cada distância e conta a frequência dos fatores, considerando cada fator único por distância
        for (Set<Integer> distanciasSet : distancias.values()) {
            for (Integer distancia : distanciasSet) {
                List<Integer> fatores = Fatoracao.fatoresPrimos(distancia);
                Set<Integer> fatoresUnicos = new HashSet<>(fatores);
                for (Integer fator : fatoresUnicos) {
                    contagemFatores.put(fator, contagemFatores.getOrDefault(fator, 0) + 1);
                }
            }
        }

        // Ordena os fatores pela sua frequência de forma descendente e seleciona o mais frequente
        return contagemFatores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(1); // Retorna 1 se nenhum fator for encontrado, o que indica um erro na análise
    }
}