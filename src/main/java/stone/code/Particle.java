package stone.code;

import java.util.ArrayList;

import stone.code.cells.CellType;
import stone.code.mazes.Maze;

public class Particle {
    public int id;
    public int turn;

    public int row;
    public int column;

    ArrayList<String> path;

    public int[] options;
    ArrayList<Integer> moveOptions;

    public Particle(int id, int turn) {
        this.id = id;
        this.turn = turn;

        row = 0;
        column = 0;

        path = new ArrayList<>();
        options = new int[4];
        moveOptions = new ArrayList<>();
    }

    public void updateMoveOptions(int[] options, ArrayList<Integer> moveOptions) {
        this.options = options;
        this.moveOptions = moveOptions;
    }

    public boolean isKamikaze(Maze maze) {
        boolean isGoingToObstacle = maze.getNextOf(row, column) == CellType.OBSTACLE;
        boolean isGoingToAnotherParticle = maze.hasParticleOn(row, column);
        return isGoingToObstacle || isGoingToAnotherParticle;
    }

    public String getFormatedPath() {
        String result = turn + ""; // TODO: Use StringBuilder or something like that

        for (String direction : path) {
            result = result + " " + direction;
        }

        return result;
    }

    public int getEmpties() {
        int empties = 0;
        for (int i = 0; i < 4; i++) {
            if (options[i] == 0) {
                empties++;
            }
        }
        return empties;
    }

    public boolean canMoveUp() {
        return options[0] == CellType.EMPTY;
    }

    public boolean canMoveRight() {
        return options[1] == CellType.EMPTY;
    }

    public boolean canMoveDown() {
        return options[2] == CellType.EMPTY;
    }

    public boolean canMoveLeft() {
        return options[3] == CellType.EMPTY;
    }

    public void up() {
        row--;
        path.add("U");
    }

    public void right() {
        column++;
        path.add("R");
    }

    public void down() {
        row++;
        path.add("D");
    }

    public void left() {
        column--;
        path.add("L");
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        Particle particle = (Particle) other;
        return id == particle.id;
    }
}
