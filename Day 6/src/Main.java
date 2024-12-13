import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] map = loadMap("src/testPuzzle.txt");

        printMap(map);
    }
    public static void printMap(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static char[][] loadMap(String filename) {
        try {
            Scanner in = new Scanner(new File(filename));
            int rows = 0;
            String[] lines = new String[130];
            while (in.hasNextLine()) {
                lines[rows] = in.nextLine();
                rows ++;
            }
            int cols = lines[0].length();
            char[][] map = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                map[i] = lines[i].toCharArray();
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new char[0][0];
        }
    }
}