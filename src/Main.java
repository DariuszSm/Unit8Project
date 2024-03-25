import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    private static String[][] maze;

    public static String[][] getMaze() {
        return maze;
    }

    public static String getCoordPoint(int y, int x) {
        return "(" + y + ", " + x + ")";
    }

    public static String turnsToAnswerFormat(String moves) {
        String assemble = "";
        if (moves.isEmpty()) {
            return assemble;
        }

        int x = 0;
        int y = 0;

        assemble += getCoordPoint(y, x);
        int i = 0;
        while (i+1 <= moves.length()) {
            assemble += " ---> ";
            if (moves.charAt(i) == 'U') {
                y--;
            } else if (moves.charAt(i) == 'D'){
                y++;
            } else if (moves.charAt(i) == 'R'){
                x++;
            } else if (moves.charAt(i) == 'L'){
                x--;
            }
            assemble += getCoordPoint(y, x);
            i++;
        }
        return assemble;
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
            if (branch.hasWon()) {
                String formatAnswer = turnsToAnswerFormat(branch.stepsTaken);
                System.out.println("Answer (in turns): " + branch.stepsTaken);
                System.out.println("Answer (in Mr. Das's super amazing format that I have mixed feelings for): " + formatAnswer);
                System.out.println("Steps taken: " + branch.stepsTaken.length());
                System.out.println("Passes short maze?: " + formatAnswer.equals(Answer.shortAnswer));
                System.out.println("Passes super maze?: " + formatAnswer.equals(Answer.superAnswer));

            }
        }

    }
}