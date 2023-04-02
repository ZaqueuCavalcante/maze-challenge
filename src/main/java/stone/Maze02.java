package stone;

public class Maze02 extends Maze {
    public Maze02() {
        super("02");
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
        return new int[] { 1305, 1010, 15 };
    }
}
