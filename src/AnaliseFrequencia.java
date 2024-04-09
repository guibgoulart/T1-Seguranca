import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnaliseFrequencia {

    public enum Idioma {
        PORTUGUES, INGLES
    }

    private static final Character[] ALFABETO = "abcdefghijklmnopqrstuvwxyz".chars().mapToObj(c -> (char)c).toArray(Character[]::new);
    private static final Map<Character, Double> FREQUENCIAS_PORT = new HashMap<>();
    private static final Map<Character, Double> FREQUENCIAS_ING = new HashMap<>();
    private static Map<Character, Double> frequenciasAtuais;
    private static String textoCifrado = "";

    static {
        inicializarFrequencias();
    }

    public AnaliseFrequencia(String textoCifrado, Idioma idioma) {
        AnaliseFrequencia.textoCifrado = textoCifrado;
        setFrequencias(idioma);
    }

    public static void setFrequencias(Idioma idioma) {
        if (idioma == Idioma.INGLES) {
            frequenciasAtuais = FREQUENCIAS_ING;
        } else {
            frequenciasAtuais = FREQUENCIAS_PORT;
        }
    }

    private static void inicializarFrequencias() {
        // Preenche o mapa de frequências para Português
        FREQUENCIAS_PORT.put('a', 14.634);
        FREQUENCIAS_PORT.put('b', 1.043);
        FREQUENCIAS_PORT.put('c', 3.882);
        FREQUENCIAS_PORT.put('d', 4.992);
        FREQUENCIAS_PORT.put('e', 12.570);
        FREQUENCIAS_PORT.put('f', 1.023);
        FREQUENCIAS_PORT.put('g', 1.303);
        FREQUENCIAS_PORT.put('h', 0.781);
        FREQUENCIAS_PORT.put('i', 6.186);
        FREQUENCIAS_PORT.put('j', 0.397);
        FREQUENCIAS_PORT.put('k', 0.015);
        FREQUENCIAS_PORT.put('l', 2.779);
        FREQUENCIAS_PORT.put('m', 4.738);
        FREQUENCIAS_PORT.put('n', 4.446);
        FREQUENCIAS_PORT.put('o', 9.735);
        FREQUENCIAS_PORT.put('p', 2.523);
        FREQUENCIAS_PORT.put('q', 1.204);
        FREQUENCIAS_PORT.put('r', 6.530);
        FREQUENCIAS_PORT.put('s', 6.805);
        FREQUENCIAS_PORT.put('t', 4.336);
        FREQUENCIAS_PORT.put('u', 3.639);
        FREQUENCIAS_PORT.put('v', 1.575);
        FREQUENCIAS_PORT.put('w', 0.037);
        FREQUENCIAS_PORT.put('x', 0.253);
        FREQUENCIAS_PORT.put('y', 0.006);
        FREQUENCIAS_PORT.put('z', 0.470);

        // Preenche o mapa de frequências para Inglês
        FREQUENCIAS_ING.put('a', 8.167);
        FREQUENCIAS_ING.put('b', 1.492);
        FREQUENCIAS_ING.put('c', 2.782);
        FREQUENCIAS_ING.put('d', 4.253);
        FREQUENCIAS_ING.put('e', 12.702);
        FREQUENCIAS_ING.put('f', 2.228);
        FREQUENCIAS_ING.put('g', 2.015);
        FREQUENCIAS_ING.put('h', 6.094);
        FREQUENCIAS_ING.put('i', 6.966);
        FREQUENCIAS_ING.put('j', 0.153);
        FREQUENCIAS_ING.put('k', 0.772);
        FREQUENCIAS_ING.put('l', 4.025);
        FREQUENCIAS_ING.put('m', 2.406);
        FREQUENCIAS_ING.put('n', 6.749);
        FREQUENCIAS_ING.put('o', 7.507);
        FREQUENCIAS_ING.put('p', 1.929);
        FREQUENCIAS_ING.put('q', 0.095);
        FREQUENCIAS_ING.put('r', 5.987);
        FREQUENCIAS_ING.put('s', 6.327);
        FREQUENCIAS_ING.put('t', 9.056);
        FREQUENCIAS_ING.put('u', 2.758);
        FREQUENCIAS_ING.put('v', 0.978);
        FREQUENCIAS_ING.put('w', 2.360);
        FREQUENCIAS_ING.put('x', 0.150);
        FREQUENCIAS_ING.put('y', 1.974);
        FREQUENCIAS_ING.put('z', 0.074);
    }

    // Divide o texto cifrado em N grupos com base no comprimento da chave
    public static List<StringBuilder> dividirEmGrupos(String textoCifrado, int comprimentoChave) {
        List<StringBuilder> grupos = new ArrayList<>();
        for (int i = 0; i < comprimentoChave; i++) {
            grupos.add(new StringBuilder());
        }

        for (int i = 0; i < textoCifrado.length(); i++) {
            char c = textoCifrado.charAt(i);
            grupos.get(i % comprimentoChave).append(c);
        }

        return grupos;
    }

    public static String analisarFrequenciaDosGrupos(List<StringBuilder> grupos, Idioma idioma) {
        setFrequencias(idioma);
        StringBuilder chave = new StringBuilder();

        for (StringBuilder grupo : grupos) {
            char letraChaveMaisProvavel = 'a';
            double minDiferenca = Double.MAX_VALUE;

            for (int deslocamento = 0; deslocamento < ALFABETO.length; deslocamento++) {
                double somaQuadradosDiferencas = 0;

                for (char letra : ALFABETO) {
                    // Correção: Calcula o deslocamento inverso para simular deciframento
                    int indiceOriginal = (letra - 'a' - deslocamento + ALFABETO.length) % ALFABETO.length;
                    char letraOriginal = ALFABETO[indiceOriginal];

                    double freqObservada = (double) grupo.chars().filter(ch -> ch == letra).count() / grupo.length();
                    double freqEsperada = frequenciasAtuais.getOrDefault(letraOriginal, 0.0);

                    somaQuadradosDiferencas += Math.pow(freqObservada - freqEsperada, 2) / freqEsperada;
                }

                if (somaQuadradosDiferencas < minDiferenca) {
                    minDiferenca = somaQuadradosDiferencas;
                    letraChaveMaisProvavel = ALFABETO[deslocamento];
                }
            }

            chave.append(letraChaveMaisProvavel);
        }

        return chave.toString();
    }
}