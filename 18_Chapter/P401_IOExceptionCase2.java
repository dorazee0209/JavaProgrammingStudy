import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.IOException;

public class P401_IOExceptionCase2 {
    public static void main(String[] args) {
        Path file = Paths.get("./Simple.txt");
        BufferedWriter writer = null;

        writer = Files.newBufferedWriter(file); // Probably IOException occurs
        writer.write('A'); // Probably IOException occurs
        writer.write('Z'); // Probably IOException occurs

        if(writer != null)
            writer.close(); // Probably IOException occurs
    }
}