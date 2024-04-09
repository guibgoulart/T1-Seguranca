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

        // Fatora cada distância e conta a frequência dos fatores
        int totalWords = 0;
        for (Set<Integer> distanciasSet : distancias.values()) {
            for (Integer distancia : distanciasSet) {
                List<Integer> fatores = Fatoracao.fatoresPrimos(distancia);
                for (Integer fator : fatores) {
                    contagemFatores.put(fator, contagemFatores.getOrDefault(fator, 0) + 1);
                    totalWords++;
                }
            }
        }

        // Ordena os fatores pela sua frequência de forma descendente
        Map<Integer, Integer> fatoresOrdenados = contagemFatores.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));

        // Imprime a frequência e porcentagem de cada fator
        int finalTotalWords = totalWords;

        fatoresOrdenados.forEach((fator, frequencia) -> {
            double percentual = (double) frequencia / finalTotalWords * 100;
            System.out.printf("Num de palavras fatorizáveis por %2d: %d (%.2f%%)\n", fator, frequencia, percentual);
        });

        // Retorna o fator mais frequente como o comprimento da chave mais provável
        return fatoresOrdenados.keySet().iterator().next();
    }
}