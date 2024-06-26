import java.util.*;

public class GBFS {
    private Set<String> dictionary;

    public GBFS(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> findPath(String start, String end) {
        if (start.length() != end.length()) {
            System.out.println("Start and end words must be the same length.");
            return null;
        }

        Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.heuristic));
        Map<String, Integer> visited = new HashMap<>();
        Set<String> allVisitedNodes = new HashSet<>(); 

        frontier.add(new Node(start, null, heuristic(start, end)));
        allVisitedNodes.add(start); 

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.word.equals(end)) {
                System.out.println("Total visited nodes: " + allVisitedNodes.size()); 
                return constructPath(current);
            }

            if (!visited.containsKey(current.word) || visited.get(current.word) > current.heuristic) {
                visited.put(current.word, current.heuristic);

                for (String neighbor : getNeighbors(current.word)) {
                    if (!visited.containsKey(neighbor) || visited.get(neighbor) > heuristic(neighbor, end)) {
                        frontier.add(new Node(neighbor, current, heuristic(neighbor, end)));
                        allVisitedNodes.add(neighbor); 
                    }
                }
            }
        }

        System.out.println("Total visited nodes: " + allVisitedNodes.size()); 
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

    private int heuristic(String current, String goal) {
        int diff = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != goal.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    private static class Node {
        String word;
        Node parent;
        int heuristic;

        Node(String word, Node parent, int heuristic) {
            this.word = word;
            this.parent = parent;
            this.heuristic = heuristic;
        }
    }
}
