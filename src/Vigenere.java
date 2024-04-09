public class Vigenere {
    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";

    // Método para descriptografar o texto cifrado usando a chave
    public static String descriptografar(String textoCifrado, String chave) {
        StringBuilder textoDescriptografado = new StringBuilder();
        chave = chave.toLowerCase();

        for (int i = 0, j = 0; i < textoCifrado.length(); i++) {
            char c = textoCifrado.charAt(i);
            if (c < 'a' || c > 'z') {
                textoDescriptografado.append(c);
                continue;
            }
            int deslocamento = chave.charAt(j) - 'a';
            // Ajuste aqui: assegura que a expressão dentro do parênteses seja sempre positiva
            char descriptografado = (char) (((c - 'a' - deslocamento + 26) % 26) + 'a');
            textoDescriptografado.append(descriptografado);
            j = (j + 1) % chave.length();
        }

        return textoDescriptografado.toString();
    }
}
