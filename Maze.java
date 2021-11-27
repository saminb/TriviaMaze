//Doors directions and states are referred to using integers 0-3.
//For directions: 0 is up, 1 is down, 2 is left, 3 is right
//For states: 0 is unopened, 1 is opened, 2 is locked, 3 is edge of the maze

import java.io.Serializable;

class Maze implements Serializable {
    private final int height, width;
    private int playerH, playerW;
    private final int goalH, goalW;
    private int[][] verDoors, horDoors;

    Maze(int myHeight, int myWidth, int myPlayerH, int myPlayerW, int myGoalH, int myGoalW) {
        height = Math.max(myHeight, 2);
        width = Math.max(myWidth, 2);

        playerH = Math.min(myPlayerH, height - 1);
        playerW = Math.min(myPlayerW, width - 1);

        goalH = Math.min(myGoalH, height - 1);
        goalW = Math.min(myGoalW, width - 1);

        verDoors = new int[height-1][width];
        horDoors = new int[height][width-1];
    }

    Maze(int myHeight, int myWidth) {
        height = Math.max(myHeight, 2);
        width = Math.max(myWidth, 2);

        playerH = (int) (Math.random() * height);
        playerW = (int) (Math.random() * width);

        //This section ensures that the randomly-selected goal is different from the starting point
        boolean cont = true;
        int tempH = 0, tempW = 0;
        while (cont) {
            tempH = (int) (Math.random() * height);
            tempW = (int) (Math.random() * width);
            if (playerH != tempH && playerW != tempW) {
                cont = false;
            }
        }
        goalH = tempH;
        goalW = tempW;

        verDoors = new int[height-1][width];
        horDoors = new int[height][width-1];
    }

    int getHeight() { return height; }

    int getWidth() { return width; }

    int getGoalH() { return goalH; }

    int getGoalW() { return goalW; }

    int getPlayerH() { return playerH; }

    int getPlayerW() { return playerW; }

    void setPlayerPosition(int myH, int myW) {
        if (myH >= 0 && myH < height) {
            playerH = myH;
        }
        if (myH >= 0 && myH < width) {
            playerW = myW;
        }
    }

    /**
     * Checks the state of a given doorway for a given room.
     * @param myH   vertical coordinate of the room
     * @param myW   horizontal coordinate of the room
     * @param myDir 0 for the upper door, 1 for lower, 2 for left, 3 for right
     * @return      returns -1 for error, 0 for unopened door, 1 for opened, 2 for locked, 3 for edge of maze
     */
    int checkDoor(int myH, int myW, int myDir) {
        int retVal = 3;

        if (myH < 0 || myH >= height || myW < 0 || myW >= width || myDir > 3 || myDir < 0) {
            return -1;
        }

        if (myDir == 0) {
            if (myH != 0) {
                retVal = verDoors[myH - 1][myW];
            }
        } else if (myDir == 1) {
            if (myH != (height - 1)) {
                retVal = verDoors[myH][myW];
            }
        } else if (myDir == 2) {
            if (myW != 0) {
                retVal = horDoors[myH][myW - 1];
            }
        } else {
            if (myW != (width - 1)) {
                retVal = horDoors[myH][myW];
            }
        }

        return retVal;
    }

    /**
     * Checks the state of all doors for a given room
     * @param myH   vertical coordinate of the room
     * @param myW   horizontal coordinate of the room
     * @return      returns an array that lists in order: upper door, lower door, left door, right door
     */
    int[] checkAllDoors(int myH, int myW) {
        int[] retArr = new int[4];
        for (int i = 0; i < 4; i++) {
            retArr[i] = checkDoor(myH, myW, i);
        }
        return retArr;
    }

    /**
     * Sets an unopened door to either opened or locked
     * @param myH   vertical coordinate of the room
     * @param myW   horizontal coordinate of the room
     * @param myDir 0 for the upper door, 1 for lower, 2 for left, 3 for right
     * @param open  true if door is being opened, false if being locked
     */
    void setDoor(int myH, int myW, int myDir, boolean open) {
        int setTo = (open)? 1 : 2;
        if (myDir == 0 && myH != 0) {

            verDoors[myH - 1][myW] = setTo;

        } else if (myDir == 1 && myH < (height - 1)) {

            verDoors[myH][myW] = setTo;

        } else if (myDir == 2 && myW != 0) {

            horDoors[myH][myW - 1] = setTo;

        } else if (myDir == 3 && myW < (width - 1)) {

            horDoors[myH][myW] = setTo;

        }
    }

    /**
     * Checks whether the maze is currently solved.
     * @return returns true if player's current position is on the goal.
     */
    boolean isSolved() {
        return (playerH == goalH && playerW == goalW);
    }

    /**
     * Checks whether the current maze is solvable from the current position. Wrapper for a recursion method.
     * @param myH   vertical coordinate of the room
     * @param myW   horizontal coordinate of the room
     * @return      returns true if the maze is still solvable
     */
    boolean solvable(int myH, int myW) {
        boolean[][] myMaze = new boolean[height][width];

        myMaze = recSolver(myH, myW, myMaze);

        return (myMaze[goalH][goalW]);
    }

    //Recursively alters a 2-d boolean array. Every room accessible from the original coordinates is set to true.
    private boolean[][] recSolver(int myH, int myW, boolean[][] myMaze) {
        myMaze[myH][myW] = true;

        //If the maze is solvable, that's all we need to know, and there's no reason to continue with recursion.
        if (myMaze[goalH][goalW]) {
            return myMaze;
        }

        int [] doors = checkAllDoors(myH, myW);

        if (doors[0] == 0 || doors[0] == 1) {
            if (!myMaze[myH - 1][myW]) {
                myMaze = recSolver(myH - 1, myW, myMaze);
            }
        }

        if (doors[1] == 0 || doors[1] == 1) {
            if (!myMaze[myH + 1][myW]) {
                myMaze = recSolver(myH + 1, myW, myMaze);
            }
        }

        if (doors[2] == 0 || doors[2] == 1) {
            if (!myMaze[myH][myW - 1]) {
                myMaze = recSolver(myH, myW - 1, myMaze);
            }
        }

        if (doors[3] == 0 || doors[3] == 1) {
            if (!myMaze[myH][myW + 1]) {
                myMaze = recSolver(myH, myW + 1, myMaze);
            }
        }

        return myMaze;
    }
}
