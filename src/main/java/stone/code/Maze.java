package stone.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import processing.core.PApplet;

public abstract class Maze {
    int turn;

    boolean open;

    String option;

    int rows;
    int columns;

    Cell startCell;
    Cell endCell;

    int[][] current;
    int[][] next;

    int[][] currentNeighbors;

    int particleCounter;
    int[][] particlesIds;
    HashMap<Integer, Particle> particles;

    public Maze(String option) {
        this.option = option;

        File file = new File("src/main/java/stone/code/mazes/maze_" + option + ".txt");
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

        particleCounter = 0;
        particlesIds = new int[rows][columns];
        particles = new HashMap<>();
        addParticle();
    }

    protected abstract void useRules(int neighbors, int row, int column);

    public abstract int[] getDrawSizes();

    public boolean currentIsStart(int row, int column) {
        return current[row][column] == CellType.START;
    }

    public boolean currentIsEmpty(int row, int column) {
        return current[row][column] == CellType.EMPTY;
    }

    public boolean currentIsEnd(int row, int column) {
        return current[row][column] == CellType.END;
    }

    public boolean startCellIsFree() {
        return particlesIds[startCell.row][startCell.column] == 0;
    }

    public boolean hasParticleOnEndCell() {
        return particlesIds[endCell.row][endCell.column] != 0;
    }

    public void close() {
        open = false;
    }

    public void addParticle() {
        // TODO: Improve this with some additional logic
        if (startCellIsFree()) {
            particleCounter++;
            particlesIds[startCell.row][startCell.column] = particleCounter;
            particles.put(particleCounter, new Particle(particleCounter, turn));
        }
    }

    public int getNextOf(int row, int column) {
        return next[row][column];
    }

    public boolean hasParticleOn(int row, int column) {
        return particlesIds[row][column] != 0;
    }

    public boolean currentIsObstacle(int row, int column) {
        return current[row][column] == CellType.OBSTACLE;
    }

    public boolean isEmpty() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (particlesIds[row][column] != 0) {
                    if (row != endCell.row && column != endCell.column) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void shift() {
        current = next;
        calculateNeighbors();
        calculateNext();
        turn++;
    }

    protected void calculateNext() {
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

    protected void calculateNeighbors() {
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

    public void draw(Game game) {
        game.translate(game.CIZE, game.CIZE);

        for (int row = 0; row < game.maze.rows; row++) {
            for (int column = 0; column < game.maze.columns; column++) {
                drawCell(game, row, column);
                drawParticle(game, row, column);
            }
        }
    }

    private void drawCell(Game game, int row, int column) {
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

    private void drawParticle(Game game, int row, int column) {
        if (particlesIds[row][column] == 0) {
            return;
        }

        game.fill(255, 0, 0);
        game.circle(column * game.CIZE + game.CIZE / 2, row * game.CIZE + game.CIZE / 2, game.CIZE / 2);
    }
}
