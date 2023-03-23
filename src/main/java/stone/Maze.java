package stone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import processing.core.PApplet;

public class Maze {
    MazeOption option;

    int rows;
    int columns;

    Cell startCell;
    Cell endCell;

    int[][] current;
    int[][] next;

    int[][] currentNeighbors;

    public Maze(MazeOption option) {
        this.option = option;

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

                    if (isStart(row, column)) {
                        startCell = new Cell(row, column, value);
                    } else if (isEnd(row, column)) {
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

                // TODO: Improve this
                switch (option) {
                    case _01:
                        useMaze01Rules(neighbors, row, column);
                        break;
                    case _02:
                        useMaze02Rules(neighbors, row, column);
                        break;
                }
            }
        }
    }

    private void useMaze01Rules(int neighbors, int row, int column) {
        if (isEmpty(row, column)) {
            if (neighbors == 2 || neighbors == 3) {
                next[row][column] = CellType.OBSTACLE;
            }
        } else if (isObstacle(row, column)) {
            if (neighbors == 4 || neighbors == 5 || neighbors == 6) {
                next[row][column] = CellType.OBSTACLE;
            }
        }
    }

    private void useMaze02Rules(int neighbors, int row, int column) {
        if (isEmpty(row, column)) {
            if (neighbors == 2 || neighbors == 3 || neighbors == 4) {
                next[row][column] = CellType.OBSTACLE;
            }
        } else if (isObstacle(row, column)) {
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

        if ((row - 1) >= 0 && (column - 1) >= 0) {
            if (isObstacle(row - 1, column - 1)) {
                neighbors++;
            }
        }
        if ((row - 1) >= 0) {
            if (isObstacle(row - 1, column)) {
                neighbors++;
            }
        }
        if ((row - 1) >= 0 && (column + 1) < columns) {
            if (isObstacle(row - 1, column + 1)) {
                neighbors++;
            }
        }

        if ((column - 1) >= 0) {
            if (isObstacle(row, column - 1)) {
                neighbors++;
            }
        }
        if ((column + 1) < columns) {
            if (isObstacle(row, column + 1)) {
                neighbors++;
            }
        }

        if ((row + 1) < rows && (column - 1) >= 0) {
            if (isObstacle(row + 1, column - 1)) {
                neighbors++;
            }
        }
        if ((row + 1) < rows) {
            if (isObstacle(row + 1, column)) {
                neighbors++;
            }
        }
        if ((row + 1) < rows && (column + 1) < columns) {
            if (isObstacle(row + 1, column + 1)) {
                neighbors++;
            }
        }

        return neighbors;
    }

    public boolean isEmpty(int row, int column) {
        return current[row][column] == CellType.EMPTY;
    }

    public boolean isObstacle(int row, int column) {
        return current[row][column] == CellType.OBSTACLE;
    }

    public boolean isStart(int row, int column) {
        return current[row][column] == CellType.START;
    }

    public boolean isEnd(int row, int column) {
        return current[row][column] == CellType.END;
    }

    public void draw(Game game) {
        game.translate(game.CIZE, game.CIZE);

        for (int row = 0; row < game.maze.rows; row++) {
            for (int column = 0; column < game.maze.columns; column++) {
                if (game.maze.isEmpty(row, column)) {
                    game.fill(200);
                }
                if (game.maze.isObstacle(row, column)) {
                    game.fill(0, 128, 0);
                }
                if (game.maze.isStart(row, column) || game.maze.isEnd(row, column)) {
                    game.fill(255, 255, 0);
                }

                game.rect(column * game.CIZE, row * game.CIZE, game.CIZE, game.CIZE, game.CIZE / 4);
            }
        }
    }
}
