package Project_word_ladder;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class word_ladder {
    private List<String> words;
    private Graph<String, DefaultWeightedEdge> graph;
    private boolean running = true;

    public word_ladder(String filename) {
        readAndBuildGraph(filename);
    }

    private void readAndBuildGraph(String filename) {
        String path = "src/main/java/Project2_6513134/";
        words = new ArrayList<>();
        graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        boolean openfilesuccess = false;

        while(!openfilesuccess){
            try {
                File file = new File(path + filename);
                Scanner scan = new Scanner(file);
                openfilesuccess = true;
                while (scan.hasNext()) {
                    String word = scan.nextLine().trim();
                    words.add(word);
                    graph.addVertex(word);
                }
                scan.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
                System.out.println("Please Input New Filename = ");
                Scanner scanner = new Scanner(System.in);
                filename = scanner.nextLine();
            }
        }

        for (String word1 : words) {
            for (String word2 : words) {
                if (!word1.equals(word2)) {
                    if (isNeighbor(word1, word2)) {
                        double weight = getTransformationCost(word1, word2);
                        graph.addEdge(word1, word2);
                        graph.setEdgeWeight(word1,word2, weight);
                    } else if (isElevator(word1, word2)) {
                        graph.addEdge(word1, word2);
                        graph.setEdgeWeight(word1,word2, 0.0);
                    }
                }
            }
        }

    }

    public void menu() {
        Scanner scan = new Scanner(System.in);
        String line;
        while (running) {
            System.out.println("=========== Main menu ===========");
            System.out.println("Enter (s = search, l = ladder, q = quit, c = searchNeighbor): ");
            line = scan.nextLine();
            System.out.println();
            if (line.equalsIgnoreCase("s")) {
                System.out.println("=========== Search Word =========");
                System.out.println("Enter word : ");
                String word = scan.nextLine();
                System.out.println();
                search(word);
            } else if (line.equalsIgnoreCase("l")) {
                System.out.println("=========== Search Ladder Word =========");
                System.out.println("Enter word_1 = ");
                String word_start = scan.nextLine();
                System.out.println("Enter word_2 = ");
                String word_end = scan.nextLine();
                System.out.println();
                findWordLadder(word_start, word_end);
            } else if(line.equalsIgnoreCase("c")) {
                searchNeighbor();
            } else if (line.equalsIgnoreCase("q")) {
                running = false;
            }


            else System.out.println("Wrong Input!!!");
        }
    }

    public void search(String searchString) {
        List<String> temp = new ArrayList<>();
        for (String word : words) {
            if (word.startsWith(searchString)) {
                temp.add(word);
            }
        }

        if (!temp.isEmpty()) {
            System.out.println("=== Available words ===");
            int count = 0;
            for (String matchedWord : temp) {
                if (count == 10) {
                    System.out.println();
                    count = 0;
                }
                count++;
                System.out.printf("%-9s",matchedWord);
            }
            System.out.println("\nCount = " + temp.size());
            System.out.println();
        } else {
            System.out.println("No words starting with '" + searchString + "' found in the file.\n");

        }
    }

    public void findWordLadder(String startWord, String endWord) {
        try {
            if (!graph.containsVertex(startWord)) {
                System.out.println("Start word '" + startWord + "' is not in the graph.\n");
                return;
            }
            if (!graph.containsVertex(endWord)) {
                System.out.println("End word '" + endWord + "' is not in the graph.\n");
                return;
            }
            DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
            GraphPath<String, DefaultWeightedEdge> path = dijkstra.getPath(startWord, endWord);

            if (path != null) {
                List<DefaultWeightedEdge> edgeList = path.getEdgeList();

                for (DefaultWeightedEdge edge : edgeList) {
                    String source = graph.getEdgeSource(edge);
                    String target = graph.getEdgeTarget(edge);
                    int weight = (int) graph.getEdgeWeight(edge);
                    if (weight == 0) {
                        System.out.println(source + " -> " + target + " (Elevator = +" + weight + ")");
                    } else {
                        System.out.println(source + " -> " + target + " (ladder   = +" + weight + ")");
                    }

                }
                System.out.println("\n" + "Transformation Cost = " + (int) path.getWeight() + "\n");
            } else {
                System.out.println("Can not transform '" + startWord + "' into '" + endWord + "'.\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public boolean isNeighbor(String a, String b) {
        if (a.length() != b.length()) return false;
        int differ = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) differ++;
            if (differ > 1) return false;
        }
        return differ == 1;
    }

    public boolean isElevator(String a, String b) {
        if (a.length() != b.length()) return false;
        char[] charArrayA = a.toCharArray();
        char[] charArrayB = b.toCharArray();
        Arrays.sort(charArrayA);
        Arrays.sort(charArrayB);
        return Arrays.equals(charArrayA, charArrayB);
    }

    private double getTransformationCost(String a, String b) {
        double cost =0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                cost = a.charAt(i) - b.charAt(i);
                if(cost<0) cost=cost*(-1);
                break;
            }
        }
        return cost;
    }

    public void searchNeighbor(){
        Scanner scan1 = new Scanner(System.in);
        String word_nei;
        System.out.println("=========== Search Neighbors =========");
        while (true) {
            System.out.println("Enter word (b = back to main menu): ");
            word_nei = scan1.nextLine();
            System.out.println();
            if (word_nei.equalsIgnoreCase("b")) {
                System.out.println("=========== Back to Main menu =========");
                System.out.println();
                break;
            }

            showNeighbors(word_nei);
        }
    }
    public void showNeighbors(String word_nei) {
        if(word_nei.length()!=5) System.out.println("Not have : "+word_nei+" in file!!");
        List<String> wordList = new ArrayList<>();
        for (String word : words)
            wordList.add(word);

        List<String> neighbors = new ArrayList<>();

        for (String otherWord : wordList) {
             if(!word_nei.equals(otherWord)){
                 if(isNeighbor(word_nei, otherWord)){
                     neighbors.add(otherWord);
                 }else if(isElevator(word_nei, otherWord)){
                     neighbors.add(otherWord);
                 }
             }
        }
        System.out.println("Neighbors of " + word_nei + ": " + neighbors);
        System.out.println();
    }


}
