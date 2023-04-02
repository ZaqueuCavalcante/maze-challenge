package stone;

public class Maze04 extends Maze {
    public Maze04() {
        super("04");
    }

    protected void useRules(int neighbors, int row, int column) {
        if (currentIsEmpty(row, column)) {
            if (neighbors == 2 || neighbors == 3 || neighbors == 4) {
                next[row][column] = CellType.OBSTACLE;
            }
        } else if (currentIsObstacle(row, column)) {
            if (neighbors == 4 || neighbors == 5) {
                next[row][column] = CellType.OBSTACLE;
            }
        }
    }

    public int[] getDrawSizes() {
        return new int[] { 970, 930, 4 };
    }
}
