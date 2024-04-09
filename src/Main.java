import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            String textoCifrado = Reader.readText("src/textos/20201-teste2.txt");

            Set<String> palavrasRepetidas = Kasiski.getRepeatingWords(textoCifrado);
            Map<String, Set<Integer>> distancias = Kasiski.getDistances(textoCifrado, palavrasRepetidas);
            int tamanhoChave = Kasiski.encontrarComprimentoChaveProvavel(distancias);

            List<StringBuilder> grupos = AnaliseFrequencia.dividirEmGrupos(textoCifrado, tamanhoChave);
            String chave = AnaliseFrequencia.analisarFrequenciaDosGrupos(grupos, AnaliseFrequencia.Idioma.PORTUGUES);

            String textoDescriptografado = Vigenere.descriptografar(textoCifrado, chave);

            System.out.println(textoDescriptografado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}