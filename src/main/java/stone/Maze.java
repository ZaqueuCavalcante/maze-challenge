package stone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import processing.core.PApplet;
import processing.core.PConstants;

public class Maze {
    MazeOption option;

    int rows;
    int columns;

    Cell startCell;
    Cell endCell;

    int[][] current;
    int[][] next;

    int[][] currentNeighbors;
    boolean showNeighbors;

    public Maze(MazeOption option) {
        this.option = option;

        showNeighbors = false;

        File file = new File("src/main/java/stone/mazes/maze" + option + ".txt");
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

                switch (option) {
                    case _00:
                        useMaze00Rules(neighbors, row, column);
                        break;
                    case _01:
                        useMaze01Rules(neighbors, row, column);
                        break;
                    case _02:
                        useMaze02Rules(neighbors, row, column);
                        break;
                    case _03:
                        useMaze03Rules(neighbors, row, column);
                        break;
                }
            }
        }
    }

    private void useMaze00Rules(int neighbors, int row, int column) {
        if (currentIsEmpty(row, column)) {
            if (neighbors == 2 || neighbors == 3) {
                next[row][column] = CellType.OBSTACLE;
            }
        } else if (currentIsObstacle(row, column)) {
            if (neighbors == 4 || neighbors == 5) {
                next[row][column] = CellType.OBSTACLE;
            }
        }
    }

    private void useMaze01Rules(int neighbors, int row, int column) {
        if (currentIsEmpty(row, column)) {
            if (neighbors == 2 || neighbors == 3) {
                next[row][column] = CellType.OBSTACLE;
            }
        } else if (currentIsObstacle(row, column)) {
            if (neighbors == 4 || neighbors == 5 || neighbors == 6) {
                next[row][column] = CellType.OBSTACLE;
            }
        }
    }

    private void useMaze02Rules(int neighbors, int row, int column) {
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

    private void useMaze03Rules(int neighbors, int row, int column) {
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

    private void calculateNeighbors() {
        currentNeighbors = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                currentNeighbors[row][column] = getNeighbors(row, column);
            }
        }
    }

    private int getNeighbors(int row, int column) {
        int neighbors = 0;

        if ((row - 1) >= 0) {
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

        if ((row + 1) < rows) {

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

        }

        return neighbors;
    }

    public boolean isSolution(String path) {
        int row = startCell.row;
        int column = startCell.column;

        for (char c : path.toCharArray()) {
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
                return false;
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
        int up = ((row - 1) >= 0 && next[row - 1][column] != CellType.OBSTACLE) ? 1 : 0;
        int right = ((column + 1) < columns && next[row][column + 1] != CellType.OBSTACLE) ? 1 : 0;
        int down = ((row + 1) < rows && next[row + 1][column] != CellType.OBSTACLE) ? 1 : 0;
        int left = ((column - 1) >= 0 && next[row][column - 1] != CellType.OBSTACLE) ? 1 : 0;

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
