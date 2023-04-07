package stone.code.mazes;

import stone.code.cells.CellType;

public class Maze03Free10x10 extends Maze {
    public Maze03Free10x10() {
        super("03_free_10x10");
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
        return new int[] { 1000, 980, 80 };
    }
}
