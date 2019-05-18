import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    private class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean ID;

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isID() {
            return ID;
        }

        public void setID(boolean ID) {
            this.ID = ID;
        }
    }
}
