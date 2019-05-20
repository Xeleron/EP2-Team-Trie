import java.io.File;
import java.util.Scanner;

public class SimulationRunner {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            Trie trie = new Trie();
            try (Scanner c = new Scanner(new File(System.getProperty("user.dir") + "/data/initial-collectors-" + i + ".csv"), "UTF-8").useDelimiter("(;|\r\n|\n)")) {
                while (c.hasNext()) trie.add(c.next(), Integer.parseInt(c.next()), Integer.parseInt(c.next()));
                query(trie, i);
                System.out.println("Simulation of ’initial-collectors-" + i + ".csv’ and ’queries-" + i + ".txt");
                System.out.print("minimal size of network: ");
                System.out.println(trie.getMinimumSize());
                System.out.print("maximal size of network: ");
                System.out.println(trie.getMaximumSize());
                System.out.print("last deactivated collector: ");
                System.out.println(trie.getLastDeactivated());
                System.out.print("last activated collector: ");
                System.out.println(trie.getLastActivated());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error bei initial-collectors-" + i + ".csv");
                System.exit(1);
            }
        }
    }

    private static void query(Trie trie, int i) {
        try (Scanner q = new Scanner(new File(System.getProperty("user.dir") + "/data/queries-" + i + ".txt"), "UTF-8").useDelimiter("(;|\r\n|\n)")) {
            while (q.hasNext()) trie.query(q.next());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error bei queries-" + i + ".csv");
            System.exit(1);
        }
    }
}
