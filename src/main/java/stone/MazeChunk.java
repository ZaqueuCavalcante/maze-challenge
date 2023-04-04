package stone;

import java.util.ArrayList;

public class MazeChunk {
    int rows;
    int columns;

    int[][] values;
    int[][] initial;

    int[][] ids;

    ArrayList<Integer>[][] options;

    public MazeChunk() {
        this.rows = 10;
        this.columns = 10;

        values = new int[rows][columns];
        values[0] = new int[] { 9, 9, 9, 9, 9, 9, 1, 9, 9, 9 };
        values[1] = new int[] { 9, 0, 9, 9, 9, 0, 1, 9, 9, 1 };
        values[2] = new int[] { 9, 9, 9, 1, 9, 9, 9, 9, 0, 9 };
        values[3] = new int[] { 9, 1, 1, 9, 9, 1, 9, 1, 9, 9 };
        values[4] = new int[] { 1, 9, 9, 9, 9, 0, 9, 9, 1, 9 };
        values[5] = new int[] { 9, 9, 9, 1, 9, 9, 9, 1, 0, 9 };
        values[6] = new int[] { 9, 1, 9, 9, 9, 9, 1, 1, 9, 9 };
        values[7] = new int[] { 1, 9, 9, 9, 9, 9, 9, 9, 1, 9 };
        values[8] = new int[] { 9, 9, 1, 9, 9, 1, 9, 1, 1, 9 };
        values[9] = new int[] { 9, 1, 9, 9, 1, 9, 9, 9, 9, 9 };

        initial = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                initial[row][column] = values[row][column];
            }
        }

        int id = 0;
        ids = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                ids[row][column] = id;
                id++;
            }
        }
    }

    public boolean isChangeable(int row, int column) {
        return initial[row][column] == 9;
    }

    public void solve() {
        values[0] = new int[] { 9, 9, 9, 9, 9, 9, 1, 9, 9, 9 };
        values[1] = new int[] { 9, 0, 9, 9, 9, 0, 1, 1, 1, 1 };
        values[2] = new int[] { 9, 9, 9, 1, 9, 9, 9, 1, 0, 9 };
        values[3] = new int[] { 9, 1, 1, 1, 1, 1, 9, 1, 9, 9 };
        values[4] = new int[] { 1, 1, 9, 1, 9, 0, 9, 1, 1, 9 };
        values[5] = new int[] { 9, 9, 9, 1, 9, 9, 9, 1, 0, 9 };
        values[6] = new int[] { 1, 1, 1, 1, 9, 9, 1, 1, 1, 9 };
        values[7] = new int[] { 1, 9, 1, 9, 9, 9, 9, 9, 1, 9 };
        values[8] = new int[] { 9, 9, 1, 1, 1, 1, 1, 1, 1, 9 };
        values[9] = new int[] { 9, 1, 1, 9, 1, 9, 9, 9, 9, 9 };
    }

    public int getCountOf(int value) {
        int count = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (values[row][column] == value) {
                    count++;
                }
            }
        }

        return count;
    }

    public int[] getDirections(int row, int column) {
        int up = (row - 1) < 0 ? CellType.OUT : values[row - 1][column];
        int right = (column + 1) == columns ? CellType.OUT : values[row][column + 1];
        int down = (row + 1) == rows ? CellType.OUT : values[row + 1][column];
        int left = (column - 1) < 0 ? CellType.OUT : values[row][column - 1];

        return new int[] { up, right, down, left };
    }
}
