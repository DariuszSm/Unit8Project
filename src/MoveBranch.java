import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MoveBranch {
    String stepsTaken;
    String possibleMovements;
    int xPos;
    int yPos;
    Boolean isPossible;
    boolean checked;
    boolean winning;

    public MoveBranch(String stepsTaken, int xPos, int yPos) {
        this.stepsTaken = stepsTaken;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setPossibleMoves(String possibleMovements) {
        this.possibleMovements = possibleMovements;
    }
    public String getPossibleMovements() {return possibleMovements;}

    private boolean isVisited(ArrayList<MoveBranch> allBranches, String stepsTaken, int xPos, int yPos) {
        for (MoveBranch branch : allBranches) {
            if (branch.xPos == xPos && branch.yPos == yPos && stepsTaken.indexOf(branch.stepsTaken) == 0) return true;
        }
        return false;
    }

    public void testBranchMovement(ArrayList<MoveBranch> allBranches) {
        int[][] results = new int[4][3];
        ArrayList<String> contPaths = new ArrayList<String>();
        ArrayList<String> allStepsTaken = new ArrayList<String>();
        ArrayList<MoveBranch> newBranches = new ArrayList<MoveBranch>();
        for (int i = 0; i < possibleMovements.length(); i++) {
            System.out.println("New Scout Dir: " + possibleMovements.substring(i, i+1));
            Scout sentScout = new Scout(stepsTaken, xPos, yPos, possibleMovements.substring(i, i+1));
            sentScout.move(Main.getMaze());
            sentScout.search(this);
            results[i] = sentScout.getResults();
            System.out.println(Arrays.toString(results[i]));
            contPaths.add(sentScout.getNearPaths());
            allStepsTaken.add(sentScout.getStepsTaken());
        }

        // check if all results are valid
        int x = 0;
        for (int i = 0; i < contPaths.size() && results[i][2] == 0; i++) {
            x++;
        }
        if (x == contPaths.size()) {
            isPossible = false;
        }

        // check if the spot has already been visited
        for (int i = 0; i < allStepsTaken.size(); i++) {
            System.out.println("Res: " + results[i][2]);
            if (results[i][2] > 0 && !isVisited(allBranches, allStepsTaken.get(i), results[i][0], results[i][1])) {
                MoveBranch branchToAdd = new MoveBranch(allStepsTaken.get(i), results[i][0], results[i][1]);
                allBranches.add(branchToAdd);
                branchToAdd.possibleMovements = contPaths.get(i);
            } else if (results[i][2] == -10) {
                winning = true;
                stepsTaken = allStepsTaken.get(i);
            }
        }
        checked = true;
    }

    public boolean hasWon() {
        return winning;
    }

    @Override
    public String toString() {
        return "MoveBranch{" +
                "stepsTaken='" + stepsTaken + '\'' +
                ", possibleMovements='" + possibleMovements + '\'' +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", isPossible=" + isPossible +
                ", checked=" + checked +
                ", winning=" + winning +
                '}';
    }
}
