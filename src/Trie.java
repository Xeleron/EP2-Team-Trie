import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private final TrieNode root = new TrieNode();
    private int currentSize;
    private int maximumSize;
    private int minimumSize;
    private int totalNetworkRequests;
    private String lastDeactivated;
    private String lastActivated;

    public static TrieNode getCollector(String id, TrieNode root) {
        TrieNode collector = root;
        for (int i = 0; i < id.length(); i++) {
            TrieNode previous = collector;
            collector = collector.children.get(id.charAt(i));
            if (collector == null) return getFirstCollector(previous);
        }
        return collector;
    }

    public static ArrayList<TrieNode> toList(TrieNode root) {
        ArrayList<TrieNode> list = new ArrayList<>();
        for (TrieNode value : root.children.values()) {
            if (value.ID != null) list.add(value);
            else list.addAll(toList(value));
        }
        return list;
    }

    private static TrieNode getFirstCollector(TrieNode root) {
        Iterator<Map.Entry<Character, TrieNode>> iterator = root.children.entrySet().iterator();
        return iterator.hasNext() ? getFirstCollector(iterator.next().getValue()) : root;
    }

    public boolean delete(String id, TrieNode root) {
        if (id.length() == 1) {
            root.children.remove(id.charAt(0));
            minimumSize = Math.min(--currentSize, minimumSize);
            return root.children.isEmpty();
        }
        if (delete(id.substring(1), root.children.get(id.charAt(0)))) root.children.remove(id.charAt(0));
        return root.children.isEmpty();
    }

    public void add(String id, int totalRequests, int partialRequests) {
        TrieNode current = root;
        for (int i = 0; i < id.length(); i++)
            current = current.children.computeIfAbsent(id.charAt(i), c -> new TrieNode());
        current.ID = id;
        current.totalRequests = totalRequests;
        current.partialRequests = partialRequests;
        maximumSize = Math.max(++currentSize, maximumSize);
        minimumSize++;
    }

    public void query(String id) {
        TrieNode collector = getCollector(id, root);
        collector.totalRequests++;
        collector.partialRequests++;
        collector.used = true;
        totalNetworkRequests++;
        if (collector.totalRequests >= 1000) {
            delete(collector.ID, root);
            lastDeactivated = collector.ID;
            add(id, 0, 0);
            minimumSize--;
        } else if (collector.partialRequests >= 250) {
            collector.partialRequests = 0;
            add(id, 0, 0);
            minimumSize--;
            lastActivated = id;
        }
        if (totalNetworkRequests % 500000 == 0) disableInactiveCollectors();
    }

    private void disableInactiveCollectors() {
        for (TrieNode node : toList(root))
            if (node.used) node.used = false;
            else delete(node.ID, root);
    }

    public int getMinimumSize() {
        return minimumSize;
    }

    public int getMaximumSize() {
        return maximumSize;
    }

    public String getLastDeactivated() {
        return lastDeactivated;
    }

    public String getLastActivated() {
        return lastActivated;
    }

    private class TrieNode {
        private final Map<Character, TrieNode> children = new TreeMap<>();
        private String ID;
        private int totalRequests;
        private int partialRequests;
        private boolean used;
    }
}
