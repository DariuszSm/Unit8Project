import java.util.Arrays;

public class Scout {
    private String stepsTaken;
    private int xPos;
    private int yPos;
    private String initAngle;
    private String currentDir;
    private String nearPaths;
    private String possibleResults;

    private int[] results;

    public Scout(String stepsTaken, int xPos, int yPos, String startingDir) {
        this.stepsTaken = stepsTaken;
        this.xPos = xPos;
        this.yPos = yPos;
        initAngle = startingDir;
        currentDir = initAngle;
    }

    private boolean hasWon(String[][] maze) {
        if (xPos == maze[0].length-1) {
            return yPos == maze.length - 1;
        }
        return false;
    }

    public static String getAllMoves(String[][] maze, int xPos, int yPos) {
        String possibles = "";
        if (yPos != 0 && maze[yPos-1][xPos].equals(".")) {
            possibles += "U";
        }
        if (yPos != maze.length-1 && maze[yPos+1][xPos].equals(".")) {
            possibles += "D";
        }
        if (xPos != 0 && maze[yPos][xPos-1].equals(".")) {
            possibles += "L";
        }
        if (xPos != maze[0].length-1 && maze[yPos][xPos+1].equals(".")) {
            possibles += "R";
        }
        return possibles;
    }

    public static String getPossibleMoves(String currentDir, String [][] maze, int xPos, int yPos) {
        String possibles = getAllMoves(maze, xPos, yPos);
//        System.out.println("Possibles: " + possibles);
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
        // System.out.println("NewPossib" + possibles);
        return possibles;
    }

    private int possiblePaths(String[][] maze) {
        return getPossibleMoves(currentDir, maze, xPos, yPos).length();
    }

    public boolean move(String[][] maze) {
        if (currentDir.equals("U") && yPos != 0 && !maze[yPos-1][xPos].equals("#")) {
            yPos--;
            stepsTaken += "U";
            return true;
        } else if (currentDir.equals("D") && yPos != maze.length-1 && !maze[yPos+1][xPos].equals("#")) {
            yPos++;
            stepsTaken += "D";
            return true;
        } else if (currentDir.equals("R") && xPos != maze[0].length-1 && !maze[yPos][xPos+1].equals("#")) {
            xPos++;
            stepsTaken += "R";
            return true;
        } else if (currentDir.equals("L") && xPos != 0 && !maze[yPos][xPos-1].equals("#")) {
            xPos--;
            stepsTaken += "L";
            return true;
        }
        return false;
    }

    private void printMaze() {
        String[][] maze = Main.getMaze();
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[r].length; c++) {
                if (r == yPos && c == xPos) {
                    System.out.print("O");
                } else {
                    System.out.print(maze[r][c]);
                }
            }
            System.out.println();
        }
    }

    private void moveCycle() {
        String[][] maze = Main.getMaze();
        boolean movingSuccessful = true;

        System.out.println(xPos);
        System.out.println(yPos);

        while ((possibleResults.length() == 1 && movingSuccessful)) {
            movingSuccessful = move(maze);

            possibleResults = getPossibleMoves(currentDir, maze, xPos, yPos);
            if (possibleResults.length() == 1) {
                currentDir = possibleResults;
            }
            System.out.println("Current Dir: " + currentDir);

            System.out.println(movingSuccessful);

            printMaze();

            possibleResults = getPossibleMoves(currentDir, maze, xPos, yPos);
            System.out.println(xPos + ", " + yPos);
            System.out.println(currentDir);
            System.out.println("EndLoop");

        }
    }

    public void search(MoveBranch currBranch) {
        this.results = new int[3];

        String[][] maze = Main.getMaze();


        possibleResults = getPossibleMoves(currentDir, maze, xPos, yPos);
        System.out.println("possibleRes: " + possibleResults);

        moveCycle();

        results[0] = xPos;
        results[1] = yPos;

        if (hasWon(maze)) results[2] = -10;
        // if met dead end
        else if (possiblePaths(maze) == 0) results[2] = 0;
        // if at fork
        else if (possiblePaths(maze) > 1) results[2] = possiblePaths(maze);
        nearPaths = possibleResults;
    }

    public int[] getResults() {
        return results;
    }

    public String getNearPaths() {
        return nearPaths;
    }

    public String getStepsTaken() {
        return stepsTaken;
    }

}
