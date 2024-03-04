public class Scout {
    String stepsTaken;
    int xPos;
    int yPos;
    String initAngle;
    String currentDir;

    int[] results;

    public Scout(String stepsTaken, int xPos, int yPos, String startingDir) {
        this.stepsTaken = stepsTaken;
        this.xPos = xPos;
        this.yPos = yPos;
        initAngle = startingDir;
        currentDir = initAngle;
    }

    public static String getPossibleMoves(String currentDir, String [][] maze, int xPos, int yPos) {
        String possibles = "";
        if (yPos != 0 && maze[yPos-1][xPos].equals(".")) {
            possibles += "U";
        } else if (yPos != maze.length-1 && maze[yPos+1][xPos].equals(".")) {
            possibles += "D";
        } else if (xPos != 0 && maze[yPos][xPos-1].equals(".")) {
            possibles += "L";
        } else if (xPos != maze[0].length-1 && maze[yPos][xPos+1].equals(".")) {
            possibles += "R";
        }
        int removeInd = 0;
        if (currentDir.contains("U")) {
            removeInd = possibles.indexOf("D");
        } else if (currentDir.contains("R")) {
            removeInd = possibles.indexOf("L");
        } else if (currentDir.contains("D")) {
            removeInd = possibles.indexOf("U");
        } else if (currentDir.contains("L")) {
            removeInd = possibles.indexOf("R");
        }
        if (removeInd != -1) {
            possibles = possibles.substring(0, removeInd) + possibles.substring(removeInd+1);
        }

        return possibles;
    }

    private boolean canMove(String currentAngle, String[][] maze) {

    }

    public void search() {
        this.results = new int[3];
        String[][] maze = Main.getMaze();
        String possibleResults = "";
        for (int i = 0; i < 4; i++) {

        }

    }

    public int[] getResults() {
        return results;
    }

}
