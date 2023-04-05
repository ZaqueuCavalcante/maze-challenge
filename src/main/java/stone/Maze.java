package stone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import processing.core.PApplet;
import processing.core.PConstants;

public abstract class Maze {
    String option;

    int rows;
    int columns;

    Cell startCell;
    Cell endCell;

    int[][] current;
    int[][] next;

    int[][] currentNeighbors;
    boolean showNeighbors;

    public Maze(String option) {
        this.option = option;
        showNeighbors = false;

        File file = new File("src/main/java/stone/data/maze_" + option + ".txt");
        InputStream input;

        try {
            input = new FileInputStream(file);

            String[] lines = PApplet.loadStrings(input);
            for (int i = 0; i < lines.length; i++) {
                lines[i] = lines[i].replaceAll(" ", "");
            }

            rows = lines.length;
            columns = lines[0].length();

            current = new int[rows][columns];

            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    int value = Integer.parseInt(String.valueOf(lines[row].charAt(column)));
                    current[row][column] = value;

                    if (currentIsStart(row, column)) {
                        startCell = new Cell(row, column, value);
                    } else if (currentIsEnd(row, column)) {
                        endCell = new Cell(row, column, value);
                    }
                }
            }

            calculateNeighbors();

            calculateNext();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void shift() {
        current = next;
        calculateNeighbors();
        calculateNext();
    }

    private void calculateNext() {
        next = new int[rows][columns];

        next[startCell.row][startCell.column] = CellType.START;
        next[endCell.row][endCell.column] = CellType.END;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int neighbors = currentNeighbors[row][column];
                useRules(neighbors, row, column);
            }
        }
    }

    protected abstract void useRules(int neighbors, int row, int column);

    public abstract int[] getDrawSizes();

    private void calculateNeighbors() {
        currentNeighbors = new int[rows][columns];

        for (int column = 0; column < columns; column++) {
            currentNeighbors[0][column] = getNeighborsTopRow(0, column);
            currentNeighbors[rows - 1][column] = getNeighborsDownRow(rows - 1, column);
        }

        for (int row = 1; row < rows - 1; row++) {
            currentNeighbors[row][0] = getNeighborsLeftColumn(row, 0);
            currentNeighbors[row][columns - 1] = getNeighborsRightColumn(row, columns - 1);
        }

        for (int row = 1; row < rows - 1; row++) {
            for (int column = 1; column < columns - 1; column++) {
                currentNeighbors[row][column] = getNeighbors(row, column);
            }
        }
    }

    private int getNeighborsTopRow(int row, int column) {
        int neighbors = 0;

        if ((column - 1) >= 0) {
            if (currentIsObstacle(row, column - 1)) {
                neighbors++;
            }
        }
        if ((column + 1) < columns) {
            if (currentIsObstacle(row, column + 1)) {
                neighbors++;
            }
        }

        if ((column - 1) >= 0) {
            if (currentIsObstacle(row + 1, column - 1)) {
                neighbors++;
            }
        }
        if (currentIsObstacle(row + 1, column)) {
            neighbors++;
        }
        if ((column + 1) < columns) {
            if (currentIsObstacle(row + 1, column + 1)) {
                neighbors++;
            }
        }

        return neighbors;
    }

    private int getNeighborsDownRow(int row, int column) {
        int neighbors = 0;

        if ((column - 1) >= 0) {
            if (currentIsObstacle(row - 1, column - 1)) {
                neighbors++;
            }
        }
        if (currentIsObstacle(row - 1, column)) {
            neighbors++;
        }
        if ((column + 1) < columns) {
            if (currentIsObstacle(row - 1, column + 1)) {
                neighbors++;
            }
        }

        if ((column - 1) >= 0) {
            if (currentIsObstacle(row, column - 1)) {
                neighbors++;
            }
        }
        if ((column + 1) < columns) {
            if (currentIsObstacle(row, column + 1)) {
                neighbors++;
            }
        }

        return neighbors;
    }

    private int getNeighborsLeftColumn(int row, int column) {
        int neighbors = 0;

        if ((row - 1) >= 0) {
            if (currentIsObstacle(row - 1, column)) {
                neighbors++;
            }
            if (currentIsObstacle(row - 1, column + 1)) {
                neighbors++;
            }
        }

        if (currentIsObstacle(row, column + 1)) {
            neighbors++;
        }

        if ((row + 1) < rows) {
            if (currentIsObstacle(row + 1, column)) {
                neighbors++;
            }
            if (currentIsObstacle(row + 1, column + 1)) {
                neighbors++;
            }
        }

        return neighbors;
    }

    private int getNeighborsRightColumn(int row, int column) {
        int neighbors = 0;

        if ((row - 1) >= 0) {
            if (currentIsObstacle(row - 1, column - 1)) {
                neighbors++;
            }
            if (currentIsObstacle(row - 1, column)) {
                neighbors++;
            }
        }

        if (currentIsObstacle(row, column - 1)) {
            neighbors++;
        }

        if ((row + 1) < rows) {
            if (currentIsObstacle(row + 1, column - 1)) {
                neighbors++;
            }
            if (currentIsObstacle(row + 1, column)) {
                neighbors++;
            }
        }

        return neighbors;
    }

    private int getNeighbors(int row, int column) {
        int neighbors = 0;

        if (currentIsObstacle(row - 1, column - 1)) {
            neighbors++;
        }
        if (currentIsObstacle(row - 1, column)) {
            neighbors++;
        }
        if (currentIsObstacle(row - 1, column + 1)) {
            neighbors++;
        }

        if (currentIsObstacle(row, column - 1)) {
            neighbors++;
        }
        if (currentIsObstacle(row, column + 1)) {
            neighbors++;
        }

        if (currentIsObstacle(row + 1, column - 1)) {
            neighbors++;
        }
        if (currentIsObstacle(row + 1, column)) {
            neighbors++;
        }
        if (currentIsObstacle(row + 1, column + 1)) {
            neighbors++;
        }

        return neighbors;
    }

    public boolean isSolution(String path, int lifes) {
        int row = startCell.row;
        int column = startCell.column;

        char[] pathArray = path.replaceAll(" ", "").toCharArray();

        for (char c : pathArray) {
            shift();

            String direction = String.valueOf(c).toString();

            if (direction.equals("U")) {
                row--;
            }
            if (direction.equals("R")) {
                column++;
            }
            if (direction.equals("D")) {
                row++;
            }
            if (direction.equals("L")) {
                column--;
            }

            if (currentIsObstacle(row, column)) {
                if (lifes == 1) {
                    return false;
                }
                lifes--;
            }
        }

        return row == endCell.row && column == endCell.column;
    }

    public boolean currentIsEmpty(int row, int column) {
        return current[row][column] == CellType.EMPTY;
    }

    public boolean currentIsObstacle(int row, int column) {
        return current[row][column] == CellType.OBSTACLE;
    }

    public boolean currentIsStart(int row, int column) {
        return current[row][column] == CellType.START;
    }

    public boolean currentIsEnd(int row, int column) {
        return current[row][column] == CellType.END;
    }

    public int[] getNextDirections(int row, int column) {
        int up = (row - 1) < 0 ? CellType.OUT : 0;
        if (up != CellType.OUT) {
            up = (next[row - 1][column] == CellType.OBSTACLE) ? CellType.OBSTACLE : CellType.EMPTY;
        }

        int right = (column + 1) == columns ? CellType.OUT : 0;
        if (right != CellType.OUT) {
            right = (next[row][column + 1] == CellType.OBSTACLE) ? CellType.OBSTACLE : CellType.EMPTY;
        }

        int down = (row + 1) == rows ? CellType.OUT : 0;
        if (down != CellType.OUT) {
            down = (next[row + 1][column] == CellType.OBSTACLE) ? CellType.OBSTACLE : CellType.EMPTY;
        }

        int left = (column - 1) < 0 ? CellType.OUT : 0;
        if (left != CellType.OUT) {
            left = (next[row][column - 1] == CellType.OBSTACLE) ? CellType.OBSTACLE : CellType.EMPTY;
        }

        return new int[] { up, right, down, left };
    }

    public void draw(Game game) {
        game.translate(game.CIZE, game.CIZE);

        for (int row = 0; row < game.maze.rows; row++) {
            for (int column = 0; column < game.maze.columns; column++) {
                drawCells(game, row, column);
                drawNeighbors(game, row, column);
            }
        }
    }

    private void drawCells(Game game, int row, int column) {
        if (game.maze.currentIsEmpty(row, column)) {
            game.fill(200);
        }
        if (game.maze.currentIsObstacle(row, column)) {
            game.fill(0, 128, 0);
        }
        if (game.maze.currentIsStart(row, column) || game.maze.currentIsEnd(row, column)) {
            game.fill(255, 255, 0);
        }

        game.rect(column * game.CIZE, row * game.CIZE, game.CIZE, game.CIZE, game.CIZE / 4);
    }

    private void drawNeighbors(Game game, int row, int column) {
        if (!showNeighbors) {
            return;
        }

        int neighbors = currentNeighbors[row][column];
        game.textSize((float) (game.CIZE * 0.40));
        game.textAlign(PConstants.CENTER);
        game.fill(0);
        game.text(neighbors, column * game.CIZE + game.CIZE / 2, (float) ((row * game.CIZE) + game.CIZE * 0.65));
    }
}
