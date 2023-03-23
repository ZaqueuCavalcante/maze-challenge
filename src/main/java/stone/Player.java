package stone;

import java.util.ArrayList;

public class Player {

    int row;
    int column;

    boolean canMoveUp;
    boolean canMoveRight;
    boolean canMoveDown;
    boolean canMoveLeft;

    boolean isDead;
    boolean wonTheGame;

    ArrayList<String> path;

    public Player(int row, int column) {
        this.row = row;
        this.column = column;
        isDead = false;
        wonTheGame = false;
        path = new ArrayList<String>();
    }

    public void updateMoveOptions(int[][] nextMaze) {
        canMoveUp = (row - 1) >= 0 && nextMaze[row - 1][column] != CellType.OBSTACLE;
        canMoveRight = (column + 1) < nextMaze[0].length && nextMaze[row][column + 1] != CellType.OBSTACLE;
        canMoveDown = (row + 1) < nextMaze.length && nextMaze[row + 1][column] != CellType.OBSTACLE;
        canMoveLeft = (column - 1) >= 0 && nextMaze[row][column - 1] != CellType.OBSTACLE;
    }

    public void resetMoveOptions() {
        canMoveUp = false;
        canMoveRight = false;
        canMoveDown = false;
        canMoveLeft = false;
    }
}
