package stone;

public class MazeChunk {
    int rows;
    int columns;

    int[][] values;

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
    }
}
