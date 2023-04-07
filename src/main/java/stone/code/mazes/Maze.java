package stone.code.mazes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import stone.code.MovesChooser;
import stone.code.Particle;
import stone.code.cells.Cell;
import stone.code.cells.CellType;
import stone.code.games.Game;

public abstract class Maze {
    int turn;

    boolean open;
    boolean particleCanAccessEndCell;

    public String option;

    public int rows;
    public int columns;

    public Cell startCell;
    public Cell endCell;

    final int[][] cellsIds;

    public int[][] current;
    public int[][] next;

    public int[][] currentNeighbors;

    int particleCounter;
    int[][] currentParticlesIds;
    public HashMap<Integer, Particle> particles;

    public ArrayList<Particle> outParticles;

    public Maze(String option) {
        this.option = option;

        open = true;
        particleCanAccessEndCell = false;

        File file = new File("src/main/java/stone/mazes/maze_" + option + ".txt");
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
        particles = new HashMap<>();
        addParticle();

        outParticles = new ArrayList<>();
    }

    protected abstract void useRules(int neighbors, int row, int column);

    public abstract int[] getDrawSizes();

    int minEmpties = 1_000_000;

    public void calculateMinEmpties() {
        int empties = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (current[row][column] == 0) {
                    empties++;
                }
            }
        }

        if (empties < minEmpties) {
            minEmpties = empties;
            System.out.println("TURN = " + turn + " --- [ ] = " + empties);
        }
    }

    public void addParticle() {
        if (open && startCellIsFree()) {
            particleCounter++;
            currentParticlesIds[startCell.row][startCell.column] = particleCounter;

            Particle particle = new Particle(particleCounter, turn);
            int[] directions = getNextDirections(particle.row, particle.column);
            int[] nextMoves = getNextCellsIds(particle.row, particle.column, directions);
            particle.updateMoveOptions(directions, nextMoves);

            particles.put(particle.id, particle);
        }
    }

    public void shift() {
        // calculateMinEmpties();

        updateParticles();

        current = next;
        calculateNeighbors();
        calculateNext();
        turn++;

        updateParticlesMoveOptions(); // ONLY FOR DEBUG

        if (outParticles.size() > 0) {
            open = false;
        }

        if (hasSpace()) {
            addParticle();
        }

        if (particles.size() == 0) {
            System.out.println("SOLVED!");

            Collections.sort(outParticles, (a, b) -> Integer.compare(a.turn, b.turn));
        }
    }

    private boolean hasSpace() {
        for (Particle p : particles.values()) {
            if (p.distanceToStart() < 4) {
                return false;
            }
        }

        return true;
    }

    private void updateParticlesMoveOptions() {
        if (particleCanAccessEndCell) {
            for (Particle p : particles.values()) {
                int[] directions = getNextDirections(p.row, p.column);
                int[] nextMoves = getNextCellsIds(p.row, p.column, directions);
                p.updateMoveOptions(directions, nextMoves);
            }
        } else {
            next[endCell.row][endCell.column] = CellType.OBSTACLE;
            for (Particle p : particles.values()) {
                int[] directions = getNextDirections(p.row, p.column);
                int[] nextMoves = getNextCellsIds(p.row, p.column, directions);
                p.updateMoveOptions(directions, nextMoves);
            }
            next[endCell.row][endCell.column] = CellType.END;
        }
    }

    public int getCellRow(int cellId) {
        int cellRow = cellId / columns;

        return cellRow;
    }

    public int getCellColumn(int cellId) {
        int cellColumn = cellId % columns;

        return cellColumn;
    }

    public void updateParticles() {
        updateParticlesMoveOptions();

        currentParticlesIds = new int[rows][columns];

        MovesChooser chooser = new MovesChooser(particles, cellsIds[endCell.row][endCell.column]);
        HashMap<Integer, Integer> moves = chooser.getMoves();

        moves.forEach((particleId, cellId) -> {
            int newRow = getCellRow(cellId);
            int newColumn = getCellColumn(cellId);
            currentParticlesIds[newRow][newColumn] = particleId;
            Particle p = particles.get(particleId);
            p.row = newRow;
            p.column = newColumn;
        });

        // Clear Maze
        for (int id : chooser.outs) {
            Particle endParticle = particles.remove(id);
            outParticles.add(endParticle);
        }
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

    public int[] getNextCellsIds(int row, int column, int[] directions) {
        int up = directions[0];
        int right = directions[1];
        int down = directions[2];
        int left = directions[3];

        int[] result = new int[4];
        result[0] = -1;
        result[1] = -1;
        result[2] = -1;
        result[3] = -1;

        if (up == CellType.EMPTY) {
            result[0] = cellsIds[row - 1][column];
        }
        if (right == CellType.EMPTY) {
            result[1] = cellsIds[row][column + 1];
        }
        if (down == CellType.EMPTY) {
            result[2] = cellsIds[row + 1][column];
        }
        if (left == CellType.EMPTY) {
            result[3] = cellsIds[row][column - 1];
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
                drawParticleMoveOptions(game, row, column);
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

    private void drawParticleMoveOptions(Game game, int row, int column) {
        if (currentParticlesIds[row][column] == 0) {
            return;
        }

        game.fill(255, 0, 0);

        float x = (float) (column * game.CIZE + game.CIZE / 2);
        float y = row * game.CIZE + game.CIZE / 2;
        float delta = game.CIZE / 4;
        float radius = game.CIZE / 4;

        Particle p = particles.get(currentParticlesIds[row][column]);

        if (p.canMoveUp()) {
            game.circle(x, y - delta, radius);
        }
        if (p.canMoveRight()) {
            game.circle(x + delta, y, radius);
        }
        if (p.canMoveDown()) {
            game.circle(x, y + delta, radius);
        }
        if (p.canMoveLeft()) {
            game.circle(x - delta, y, radius);
        }
    }
}
