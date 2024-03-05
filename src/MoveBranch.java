import java.lang.reflect.Array;
import java.util.ArrayList;

public class MoveBranch {
    String stepsTaken;
    String possibleMovements;
    int xPos;
    int yPos;
    boolean isPossible;
    boolean checked;

    public MoveBranch(String stepsTaken, int xPos, int yPos) {
        this.stepsTaken = stepsTaken;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setPossibleMoves(String possibleMovements) {
        this.possibleMovements = possibleMovements;
    }

    public void testMovements() {
        int[][] results = new int[4][3];
        ArrayList<String> contPaths = new ArrayList<String>();
        for (int i = 0; i < possibleMovements.length(); i++) {
            Scout sentScout = new Scout(stepsTaken, xPos, yPos, possibleMovements.substring(i, i+1));
            sentScout.search();
            results[i] = sentScout.getResults();
            contPaths.add(sentScout.getNearPaths());
        }
        int x = 0;
        for (int[] i = results[x]; i != null && i[2] == 0; i = results[x++]);
        if (results[x] != null) isPossible = false;

    }
}
