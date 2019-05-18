import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimulationRunner {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            Trie trie = new Trie();
            try (Scanner s = new Scanner(new File("./data/initial-collectors-" + i + ".csv"), "UTF-8").useDelimiter(";")) {
                trie.add(s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()));
            } catch (FileNotFoundException e) {
                System.out.println("Error bei initial-collectors-" + i + ".csv");
                System.exit(1);
            }
        }
    }
}
