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

                    if (value == CellType.START) {
                        startCell = new Cell(row, column, value);
                    } else if (value == CellType.END) {
                        endCell = new Cell(row, column, value);
                    }
                }
            }

            calculateNext();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void shift() {
        current = next;
        calculateNext();
    }

    public void calculateNext() {
        next = new int[rows][columns];

        next[startCell.row][startCell.column] = CellType.START;
        next[endCell.row][endCell.column] = CellType.END;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int neighbors = getNeighbors(row, column, current);

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

    public void useMaze01Rules(int neighbors, int row, int column) {
        if (current[row][column] == CellType.EMPTY) {
            if (neighbors == 2 || neighbors == 3) {
                next[row][column] = 1;
            }
        } else if (current[row][column] == CellType.OBSTACLE) {
            if (neighbors == 4 || neighbors == 5 || neighbors == 6) {
                next[row][column] = 1;
            }
        }
    }

    public void useMaze02Rules(int neighbors, int row, int column) {
        if (current[row][column] == CellType.EMPTY) {
            if (neighbors == 2 || neighbors == 3 || neighbors == 4) {
                next[row][column] = 1;
            }
        } else if (current[row][column] == CellType.OBSTACLE) {
            if (neighbors == 4 || neighbors == 5) {
                next[row][column] = 1;
            }
        }
    }

    public int getNeighbors(int row, int column, int[][] maze) {
        int neighbors = 0;

        if ((row - 1) >= 0 && (column - 1) >= 0) {
            if (maze[row - 1][column - 1] == CellType.OBSTACLE) {
                neighbors++;
            }
        }
        if ((row - 1) >= 0) {
            if (maze[row - 1][column] == CellType.OBSTACLE) {
                neighbors++;
            }
        }
        if ((row - 1) >= 0 && (column + 1) < columns) {
            if (maze[row - 1][column + 1] == CellType.OBSTACLE) {
                neighbors++;
            }
        }

        if ((column - 1) >= 0) {
            if (maze[row][column - 1] == CellType.OBSTACLE) {
                neighbors++;
            }
        }
        if ((column + 1) < columns) {
            if (maze[row][column + 1] == CellType.OBSTACLE) {
                neighbors++;
            }
        }

        if ((row + 1) < rows && (column - 1) >= 0) {
            if (maze[row + 1][column - 1] == CellType.OBSTACLE) {
                neighbors++;
            }
        }
        if ((row + 1) < rows) {
            if (maze[row + 1][column] == CellType.OBSTACLE) {
                neighbors++;
            }
        }
        if ((row + 1) < rows && (column + 1) < columns) {
            if (maze[row + 1][column + 1] == CellType.OBSTACLE) {
                neighbors++;
            }
        }

        return neighbors;
    }
}
