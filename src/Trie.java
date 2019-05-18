import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;
    private int currentSize;
    private int maximumSize;
    private int minimumSize;

    public Trie() {
        root = new TrieNode();
    }

    public void add(String id, int totalRequests, int partialRequests) {
        TrieNode current = root;
        for (int i = 0; i < id.length(); i++) {
            current = current.getChildren().computeIfAbsent(id.charAt(i), c -> new TrieNode());
        }
        current.setID(true);
        current.setTotalRequests(totalRequests);
        current.setPartialRequests(partialRequests);
        currentSize++;
        maximumSize++;
        minimumSize++;
    }

    private class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean ID;
        private int totalRequests;
        private int partialRequests;

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public void setPartialRequests(int partialRequests) {
            this.partialRequests = partialRequests;
        }

        public void setTotalRequests(int totalRequests) {
            this.totalRequests = totalRequests;
        }

        public boolean isID() {
            return ID;
        }

        public void setID(boolean ID) {
            this.ID = ID;
        }
    }
}
