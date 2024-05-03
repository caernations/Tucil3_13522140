import java.io.*;
import java.util.*;

public class Main {
    private static Set<String> dictionary = new HashSet<>();

    public static void main(String[] args) throws IOException {
        loadDictionary("words.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter start word:");
        String start = scanner.nextLine().trim();
        System.out.println("Enter end word:");
        String end = scanner.nextLine().trim();
        System.out.println("Choose algorithm: 1 for UCS, 2 for GBFS, 3 for A*");
        int choice = scanner.nextInt();

        List<String> path = null;
        long startTime = System.currentTimeMillis();
        switch (choice) {
            case 1:
                UCS ucs = new UCS(dictionary);
                path = ucs.findPath(start, end);
                break;
            case 2:
                GBFS gbfs = new GBFS(dictionary);
                path = gbfs.findPath(start, end);
                break;
            case 3:
                AStar aStar = new AStar(dictionary);
                path = aStar.findPath(start, end);
                break;
            default:
                System.out.println("Invalid choice.");
                System.exit(1);
        }
        long endTime = System.currentTimeMillis();

        if (path != null) {
            System.out.println("Path: " + path);
            System.out.println("Nodes visited: " + path.size());
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
        } else {
            System.out.println("No path found.");
        }
    }

    private static void loadDictionary(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            dictionary.add(line.trim().toLowerCase());
        }
        reader.close();
    }
}