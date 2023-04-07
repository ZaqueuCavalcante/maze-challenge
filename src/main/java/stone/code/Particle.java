package stone.code;

import java.util.ArrayList;

import stone.code.cells.CellType;
import stone.code.mazes.Maze;

public class Particle {
    public int turn;

    public int row;
    public int column;

    ArrayList<String> path;

    public int[] options;
    public int[] moveOptions;

    public Tree tree;

    public Particle(int turn, Maze maze) {
        this.turn = turn;

        row = 0;
        column = 0;

        path = new ArrayList<>();
        options = new int[4];
        moveOptions = new int[4];

        tree = new Tree(maze);
    }

    public void updateMoveOptions(int[] options, int[] moveOptions) {
        this.options = options;
        this.moveOptions = moveOptions;

        if (getEmpties() == 0) {
            System.out.println("Stucked | turn = " + turn + " | (" + row + ", " + column + ")");
        }
    }

    public int distanceToStart() {
        return row + column;
    }

    public String getFormatedPath() {
        String result = turn + "";

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
        return turn;
    }

    @Override
    public boolean equals(Object other) {
        Particle particle = (Particle) other;
        return turn == particle.turn;
    }
}
