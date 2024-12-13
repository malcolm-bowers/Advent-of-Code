import java.io.File;
import java.util.Scanner;

public class Main {
    public static int searchWord(char[][] grid, String word) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                count += searchAllDirections(grid, word, i, j);
            }
        }
        return count;
    }

    public static int countXShapeMAS(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (isXShape(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isXShape(char[][] grid, int row, int col) {
        return (
                (grid[row][col] == 'A' &&
                        grid[row - 1][col - 1] == 'M' &&
                        grid[row - 1][col + 1] == 'S' &&
                        grid[row + 1][col - 1] == 'M' &&
                        grid[row + 1][col + 1] == 'S') ||

                (grid[row][col] == 'A' &&
                        grid[row - 1][col - 1] == 'S' &&
                        grid[row - 1][col + 1] == 'S' &&
                        grid[row + 1][col - 1] == 'M' &&
                        grid[row + 1][col + 1] == 'M') ||

                (grid[row][col] == 'A' &&
                        grid[row - 1][col - 1] == 'S' &&
                        grid[row - 1][col + 1] == 'M' &&
                        grid[row + 1][col - 1] == 'S' &&
                        grid[row + 1][col + 1] == 'M') ||

                (grid[row][col] == 'A' &&
                        grid[row - 1][col - 1] == 'M' &&
                        grid[row - 1][col + 1] == 'M' &&
                        grid[row + 1][col - 1] == 'S' &&
                        grid[row + 1][col + 1] == 'S')
        );
    }

    private static int searchAllDirections(char[][] grid, String word, int row, int col) {
        int wordLength = word.length();
        int count = 0;

        // Define all 8 possible directions
        int[] rowDir = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Check in all directions
        for (int d = 0; d < 8; d++) {
            int k, newRow = row, newCol = col;

            // Traverse the word
            for (k = 0; k < wordLength; k++) {
                // Check boundaries
                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length) {
                    break;
                }

                // Check if current character matches
                if (grid[newRow][newCol] != word.charAt(k)) {
                    break;
                }

                // Move in the current direction
                newRow += rowDir[d];
                newCol += colDir[d];
            }

            // If the entire word was found, increase the count
            if (k == wordLength) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        char[][] grid = loadGrid("src/puzzle.txt");

        String word = "XMAS";
        int timesWordFound = searchWord(grid, word);

        if (timesWordFound > 0) {
            System.out.println("Word found " + timesWordFound + " times!");
        } else {
            System.out.println("Word not found!");
        }

        int xShapeCount = countXShapeMAS(grid);

        printGrid(grid);

        System.out.println("The sequence MAS forms an X shape " + xShapeCount + " times.");
    }

    public static void printGrid(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char[][] loadGrid(String fileName) {
        try {
            Scanner in = new Scanner(new File(fileName));
            int rows = 0;
            String[] lines = new String[140];
            while (in.hasNextLine()) {
                lines[rows] = in.nextLine();
                rows++;
            }
            int cols = lines[0].length();
            char[][] grid = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                grid[i] = lines[i].toCharArray();
            }

            return grid;
        } catch (Exception e) {
            e.printStackTrace();
            return new char[0][0];
        }
    }
}
