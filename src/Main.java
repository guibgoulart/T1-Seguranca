import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String text = Reader.readText("src/textos/cipher1.txt");
            System.out.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}