import java.util.*;

public class AStar {
    private Set<String> dictionary;

    public AStar(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> findPath(String start, String end) {
        if (start.length() != end.length()) {
            System.out.println("Start and end words must be the same length.");
            return null;
        }

        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));
        Map<String, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);
        Map<String, Node> cameFrom = new HashMap<>();
        Set<String> visitedNodes = new HashSet<>();  // To track all visited nodes

        frontier.add(new Node(start, null, 0, heuristic(start, end)));
        visitedNodes.add(start);  // Add the start node to visited nodes

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.word.equals(end)) {
                System.out.println("Total visited nodes: " + visitedNodes.size());  // Print the number of visited nodes
                return constructPath(current);
            }

            for (String neighbor : getNeighbors(current.word)) {
                if (!visitedNodes.contains(neighbor)) {
                    visitedNodes.add(neighbor);  // Add new nodes to visited nodes
                }
                int tentativeGScore = gScore.get(current.word) + 1;
                if (!gScore.containsKey(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    int fScore = tentativeGScore + heuristic(neighbor, end);
                    frontier.add(new Node(neighbor, current, tentativeGScore, fScore));
                }
            }
        }
        System.out.println("Total visited nodes: " + visitedNodes.size());  // Print the number of visited nodes
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
        int gScore;
        int fScore;

        Node(String word, Node parent, int gScore, int fScore) {
            this.word = word;
            this.parent = parent;
            this.gScore = gScore;
            this.fScore = fScore;
        }
    }
}
