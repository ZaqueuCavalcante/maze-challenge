package stone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import processing.core.PApplet;

public class MazeChallenge04Mini extends Maze {
    public MazeChallenge04Mini() {
        this.option = "challenge_04_mini";
        showNeighbors = false;

        File file = new File("src/main/java/stone/data/maze_" + option + ".txt");
        InputStream input;

        try {
            input = new FileInputStream(file);

            String[] lines = PApplet.loadStrings(input);
            for (int i = 0; i < lines.length; i++) {
                lines[i] = lines[i].replaceAll(" ", "").replaceAll("x", "9");
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

            // Fix maze chunk
            MazeChunk chunk = new MazeChunk();
            chunk.solve();
            for (int row = 230; row < 240; row++) {
                for (int column = 230; column < 240; column++) {
                    int value = chunk.values[row - 230][column - 230];
                    current[row][column] = value;
                }
            }
            // Fix maze chunk

            calculateNeighbors();

            calculateNext();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        return new int[] { 1305, 1010, 5 };
    }
}
