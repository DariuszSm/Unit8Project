import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    private static String[][] maze;

    public static String[][] getMaze() {
        return maze;
    }

    public static void main(String[] args) {
        maze = MazeSolve.getMaze("input/maze.txt");
        ArrayList<MoveBranch> branches = new ArrayList<MoveBranch>();

        branches.add(new MoveBranch("", 0, 0));
        branches.get(0).setPossibleMoves(Scout.getAllMoves(maze, 0, 0));
        for (int i = 0; i < branches.size(); i++) {
            System.out.println("-----Branch " + (i+1) + "-----");
            branches.get(i).testBranchMovement(branches);
        }

        for (MoveBranch branch : branches) {
//            if (branch.hasWon()) {
//                System.out.println(branch.stepsTaken);
//                System.out.println("Steps taken: " + branch.stepsTaken.length());
//            }
            System.out.println(branch);
        }

    }
}