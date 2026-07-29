import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.IOException;

public class P410_FinallyCase {
    public static void main(String[] args) {
        Path file = Paths.get("./Simle.txt");
        BufferedWriter writer = null;

        try {
            writer = Files.newBufferedWriter(file);
            writer.write("A");
            writer.write("Z");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(writer != null)
                writer.close();
        }
    }
}