import java.util.ArrayList;

public class MoveBranch {
    String stepsTaken;
    String possibleMovements;
    int xPos;
    int yPos;
    boolean isPossible;

    public MoveBranch(String stepsTaken, String possibleMovements, int xPos, int yPos) {
        this.stepsTaken = stepsTaken;
        this.possibleMovements = possibleMovements;
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
