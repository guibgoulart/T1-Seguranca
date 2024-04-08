import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {
    public static String readText(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
