import java.util.Arrays;

public class Scout {
    String stepsTaken;
    int xPos;
    int yPos;
    String initAngle;
    String currentDir;
    String nearPaths;

    int[] results;

    public Scout(String stepsTaken, int xPos, int yPos, String startingDir) {
        this.stepsTaken = stepsTaken;
        this.xPos = xPos;
        this.yPos = yPos;
        initAngle = startingDir;
        currentDir = initAngle;
        search();
    }

    private boolean hasWon(String[][] maze) {
        return (xPos == maze[0].length-1 && yPos == maze.length-1);
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

    private int possiblePaths(String[][] maze) {
        return getPossibleMoves(currentDir, maze, xPos, yPos).length();
    }

    private boolean move(String[][] maze) {
        if (currentDir.equals("U") && yPos != 0 && maze[yPos-1][xPos].equals("#")) {
            yPos--;
            return true;
        } else if (currentDir.equals("D") && yPos != maze.length-1 && maze[yPos+1][xPos].equals("#")) {
            yPos++;
            return true;
        } else if (currentDir.equals("R") && xPos != maze.length-1 && maze[yPos][xPos+1].equals("#")) {
            xPos++;
            return true;
        } else if (currentDir.equals("L") && xPos != 0 && maze[yPos][xPos-1].equals("#")) {
            xPos--;
            return true;
        }
        return false;
    }

    public void search() {
        this.results = new int[3];
        String[][] maze = Main.getMaze();
        String possibleResults = getPossibleMoves(currentDir, maze, xPos, yPos);
        boolean movingSuccessful = true;

        while (possiblePaths(maze) == 1 && movingSuccessful) {
            currentDir = getPossibleMoves(currentDir, maze, xPos, yPos);
            movingSuccessful = move(maze);

            possibleResults = getPossibleMoves(currentDir, maze, xPos, yPos);
            System.out.println(Arrays.deepToString(maze));
            System.out.println(xPos + ", " + yPos);
            System.out.println(currentDir);
        }

        results[0] = xPos;
        results[1] = yPos;

        if (hasWon(maze)) results[2] = -10;
        // if met dead end
        if (possiblePaths(maze) == 0) results[2] = 0;
        // if at fork
        if (possiblePaths(maze) > 1) results[2] = possiblePaths(maze);
        nearPaths = possibleResults;
    }

    public int[] getResults() {
        return results;
    }

    public String getNearPaths() {
        return nearPaths;
    }
}
