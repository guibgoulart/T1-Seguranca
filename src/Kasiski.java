import java.util.*;

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



}