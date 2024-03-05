import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    private static String[][] maze;

    public static String[][] getMaze() {
        return maze;
    }

    public static void main(String[] args) {
        maze = MazeSolve.getMaze("input/maze");
        ArrayList<MoveBranch> branches = new ArrayList<MoveBranch>();

        branches.add(new MoveBranch("", 0, 0));
        branches.get(0).setPossibleMoves(Scout.getPossibleMoves("U", maze, 0, 0));

    }
}