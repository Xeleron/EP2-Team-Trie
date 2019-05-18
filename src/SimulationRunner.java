import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimulationRunner {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            Trie trie = new Trie();
            try (Scanner s = new Scanner(new File("./data/initial-collectors-" + i + ".csv"), "UTF-8").useDelimiter(";")) {

            } catch (FileNotFoundException e) {
                System.out.println("initial-collectors-" + i + ".csv wurde nicht gefunden");
                System.exit(1);
            }
        }
    }
}
