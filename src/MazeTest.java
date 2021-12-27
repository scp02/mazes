public class MazeTest {

    public static void main(String[] args) {
        System.out.println("Original Maze:");
        MazeSolver myMaze = new MazeSolver("maze.txt");

        if (!myMaze.mazeTraversal(MazeSolver.rowStart, MazeSolver.colStart))
            System.out.println("Maze has no solution.");
    }

}
