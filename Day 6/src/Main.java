import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] map = loadMap("Day 6/src/puzzle.txt");
        if (map.length == 0 || map[0].length == 0) {
            System.out.println("Map could not be loaded.");
            return;
        }

        System.out.println("Initial Map:");
        printMap(map);

        int steps = moveAndCountSteps(map);

        System.out.println("Total steps taken: " + steps);
    }
    public static void printMap(char[][] map) {
        for (char[] row : map) {
            for (char cell : row) {
                System.out.print(cell + " ");
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

    public static int moveAndCountSteps(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;

        int x = -1, y = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == '^') {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x != -1) break;
        }

        if (x == -1 || y == -1) {
            System.out.println("Starting position '^' not found.");
            return 0;
        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int directionIndex = 0;

        HashSet<String> visitedLocations = new HashSet<>();
        visitedLocations.add(x + "," + y); // Mark initial position as visited

        while(true) {
            int nextX = x + directions[directionIndex][0];
            int nextY = y + directions[directionIndex][1];

            System.out.println("Trying to move to: (" + nextX + ", " + nextY + ")");

            if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && map[nextX][nextY] != '#') {
                map[x][y] = '.';
                x = nextX;
                y = nextY;
                map[x][y] = '^';
                visitedLocations.add(x + "," + y);
            } else {
                directionIndex = (directionIndex + 1) % directions.length;
                System.out.println("Rotated to direction index: " + directionIndex);
            }

            if (x + directions[directionIndex][0] < 0 || x + directions[directionIndex][0] >= rows ||
                    y + directions[directionIndex][1] < 0 || y + directions[directionIndex][1] >= cols) {
                break; // Exit the grid
            }
        }
        return visitedLocations.size();
    }
}