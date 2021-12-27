import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeSolver {

    public static int rowStart;
    public static int colStart = 0;

    private static final int RIGHT = 0;
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    private int moveNumber = 0; //

    private char[][] mazeArray; //

    private Scanner input; //

    public MazeSolver(String filename) {

        // Try to open and read the file
        try {
            input = new Scanner(new File(filename));

            // The number of rows and columns are input as the first 2 lines
            int rows = input.nextInt();
            int columns = input.nextInt();

            // Create a matrix of char using the rows and columns from above
            mazeArray = new char[rows][columns];

            for (int row = 0; row < mazeArray.length; row++) {
                mazeArray[row] = input.next().toCharArray();

                // Check if there is a '.' in the first column to keep track of the starting row
                if (mazeArray[row][0] == '.')
                    rowStart = row;
            }
            printMaze();
            input.close();

            // Reassign the input to pint to the keyboard for input
            input = new Scanner(System.in);

        }
        catch (FileNotFoundException error) {
            System.out.println("File not found" + error);
        }

    }

    private void printMaze() {
        for (char[] row: mazeArray) {
            for (char cell : row)
                System.out.print(" " + cell);
            System.out.println();
        }
        System.out.println();

    }

    public boolean mazeTraversal(int row, int column) {
        // Take the step, mark it, and display it
        mazeArray[row][column] = 'x';
        printMaze();
        moveNumber++;

        // If program is at the start --> no possible solutions
        if (row == rowStart && column == colStart && moveNumber > 1) {
            System.out.println("Returned to starting location");
            return false;
        }
        // If the maze has been exited the program is done
        else if (mazeExited(column)) {
            System.out.println("Maze successfully exited!");
            return true;
        }
        else {
            System.out.printf("Total moves: %d - Press 'Return' or 'Enter' to continue...", moveNumber);
            input.nextLine();

            if (!nextMove(row, column)) {
                mazeArray[row][column] = '0';
                printMaze();

                System.out.printf("Total moves: %d - Press 'Return' or 'Enter' to continue...", moveNumber);
                input.nextLine();

                return false;
            }
            return true;
        }
    }

    private boolean nextMove(int row, int column) {
        int dRow = 0;
        int dCol = 0;

        for (int count = 0; count < 4; count++) {
            switch (count) {
                case RIGHT -> {
                    dRow = 0;
                    dCol = 1;
                }
                case UP -> {
                    dRow = -1;
                    dCol = 0;
                }
                case DOWN -> {
                    dRow = 1;
                    dCol = 0;
                }
                case LEFT -> {
                    dRow = 0;
                    dCol = -1;
                }
            }

            // Check if the move from above is valid
            if (validMove(row + dRow, column + dCol))
                //
                if (mazeTraversal(row + dRow, column + dCol))
                    return true;
        }

        return false;
    }

    private boolean validMove(int row, int column) {
        return ((row>= 0) && (row < mazeArray.length) && (column >= 0) && (column < mazeArray[row].length) && (mazeArray[row][column] == '.'));
    }

    private boolean mazeExited(int column) {
        return column == mazeArray.length - 1;
    }
}
