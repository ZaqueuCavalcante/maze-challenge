package stone.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;

public abstract class Maze {
    int turn;

    boolean open;

    String option;

    int rows;
    int columns;

    Cell startCell;
    Cell endCell;

    final int[][] cellsIds;

    int[][] current;
    int[][] next;

    int[][] currentNeighbors;

    int particleCounter;
    int[][] currentParticlesIds;
    int[][] nextParticlesIds;
    HashMap<Integer, Particle> particles;

    ArrayList<Particle> outParticles;

    public Maze(String option) {
        this.option = option;

        open = true;

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

        int id = 0;
        cellsIds = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cellsIds[row][column] = id;
                id++;
            }
        }

        particleCounter = 0;
        currentParticlesIds = new int[rows][columns];
        nextParticlesIds = new int[rows][columns];
        particles = new HashMap<>();
        addParticle();

        outParticles = new ArrayList<>();
    }

    ArrayList<Integer> emptiesList = new ArrayList<>();
    ArrayList<Integer> obstaclesList = new ArrayList<>();

    protected abstract void useRules(int neighbors, int row, int column);

    public abstract int[] getDrawSizes();

    public void shift() {
        int empties = 0;
        int obstacles = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (current[row][column] == 0) {
                    empties++;
                } else if (current[row][column] == 1) {
                    obstacles++;
                }
            }
        }
        emptiesList.add(empties);
        obstaclesList.add(obstacles);

        System.out.println("TURN = " + turn + " --- [ ] = " + empties + " | [x] = " + obstacles + " | % [ ] = "
                + (float) empties / (empties + obstacles));

        // updateParticles();
        current = next;
        calculateNeighbors();
        calculateNext();
        turn++;

        if (outParticles.size() > 0) {
            open = false;
        }

        ArrayList<Integer> turns = new ArrayList<>(Arrays.asList());
        if (turns.contains(turn)) {
            addParticle();
        }

        if (particles.size() == 0) {
            System.out.println("SOLVED!");

            Collections.sort(outParticles, (a, b) -> Integer.compare(a.turn, b.turn));

            for (Particle p : outParticles) {
                System.out.println(p.getFormatedPath());
            }
        }
    }

    public void updateParticles() {
        nextParticlesIds = new int[rows][columns];

        for (Particle p : particles.values()) {
            int[] directions = getNextDirections(p.row, p.column);
            ArrayList<Integer> nextMoves = getNextCellsIds(p.row, p.column);
            p.updateMoveOptions(directions, nextMoves);
        }

        ArrayList<Integer> outs = new ArrayList<>();

        for (Particle p : particles.values()) {
            // Error here!
            if (p.canMoveRight() && nextParticlesIds[p.row][p.column + 1] == 0) {
                p.right();
            } else if (p.canMoveDown() && nextParticlesIds[p.row + 1][p.column] == 0) {
                p.down();
            } else if (p.canMoveLeft() && nextParticlesIds[p.row][p.column - 1] == 0) {
                p.left();
            } else if (p.canMoveUp() && nextParticlesIds[p.row - 1][p.column] == 0) {
                p.up();
            } else {
                System.out.println("Stucked particle | id = " + p.id);
            }
            nextParticlesIds[p.row][p.column] = p.id;

            // Handle end
            if (p.row == endCell.row && p.column == endCell.column) {
                nextParticlesIds[p.row][p.column] = 0;
                outs.add(p.id);
            }
        }

        for (int id : outs) {
            Particle endParticle = particles.remove(id);
            outParticles.add(endParticle);
        }

        currentParticlesIds = nextParticlesIds;
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

    public ArrayList<Integer> getNextCellsIds(int row, int column) {
        int[] directions = getNextDirections(row, column);
        int up = directions[0];
        int right = directions[1];
        int down = directions[2];
        int left = directions[3];

        ArrayList<Integer> result = new ArrayList<>();

        if (up == CellType.EMPTY) {
            result.add(cellsIds[row - 1][column]);
        }
        if (right == CellType.EMPTY) {
            result.add(cellsIds[row][column + 1]);
        }
        if (down == CellType.EMPTY) {
            result.add(cellsIds[row + 1][column]);
        }
        if (left == CellType.EMPTY) {
            result.add(cellsIds[row][column - 1]);
        }

        return result;
    }

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
        return currentParticlesIds[startCell.row][startCell.column] == 0;
    }

    public boolean hasParticleOnEndCell() {
        return currentParticlesIds[endCell.row][endCell.column] != 0;
    }

    public void addParticle() {
        // TODO: Improve this with some additional logic
        if (open && startCellIsFree()) {
            particleCounter++;
            currentParticlesIds[startCell.row][startCell.column] = particleCounter;
            particles.put(particleCounter, new Particle(particleCounter, turn));
        }
    }

    public int getNextOf(int row, int column) {
        return next[row][column];
    }

    public boolean hasParticleOn(int row, int column) {
        return currentParticlesIds[row][column] != 0;
    }

    public boolean currentIsObstacle(int row, int column) {
        return current[row][column] == CellType.OBSTACLE;
    }

    public boolean isEmpty() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (currentParticlesIds[row][column] != 0) {
                    if (row != endCell.row && column != endCell.column) {
                        return false;
                    }
                }
            }
        }
        return true;
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
        if (game.maze.currentIsStart(row, column)) {
            if (open) {
                game.fill(255, 255, 0);
            } else {
                game.fill(255, 255, 200);
            }
        }
        if (game.maze.currentIsEnd(row, column)) {
            if (open || particles.size() == 0) {
                game.fill(255, 255, 200);
            } else {
                game.fill(255, 255, 0);
            }
        }

        game.rect(column * game.CIZE, row * game.CIZE, game.CIZE, game.CIZE, game.CIZE / 4);
    }

    private void drawParticle(Game game, int row, int column) {
        if (currentParticlesIds[row][column] == 0) {
            return;
        }

        game.fill(255, 0, 0);
        game.circle(column * game.CIZE + game.CIZE / 2, row * game.CIZE + game.CIZE / 2, game.CIZE / 2);

        game.textSize((float) (game.CIZE * 0.35));
        game.textAlign(PConstants.CENTER);
        game.fill(0);
        game.text(currentParticlesIds[row][column], (float) (column * game.CIZE + game.CIZE * 0.48),
                (float) ((row * game.CIZE) + game.CIZE * 0.63));
    }
}
