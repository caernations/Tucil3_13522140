import java.util.*;

public class UCS {
    private Set<String> dictionary;

    public UCS(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> findPath(String start, String end) {
        if (start.length() != end.length()) {
            System.out.println("Start and end words must be the same length.");
            return null;
        }

        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        Map<String, Integer> visited = new HashMap<>();
        frontier.add(new Node(start, null, 0));

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.word.equals(end)) {
                return constructPath(current);
            }

            for (String neighbor : getNeighbors(current.word)) {
                if (!visited.containsKey(neighbor) || visited.get(neighbor) > current.cost + 1) {
                    visited.put(neighbor, current.cost + 1);
                    frontier.add(new Node(neighbor, current, current.cost + 1));
                }
            }
        }
        return null;
    }

    private List<String> constructPath(Node node) {
        LinkedList<String> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node.word);
            node = node.parent;
        }
        return path;
    }

    private List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (dictionary.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = oldChar;
        }
        return neighbors;
    }

    private static class Node {
        String word;
        Node parent;
        int cost;

        Node(String word, Node parent, int cost) {
            this.word = word;
            this.parent = parent;
            this.cost = cost;
        }
    }
}
