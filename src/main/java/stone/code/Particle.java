package stone.code;

import java.util.ArrayList;

public class Particle {
    int id; // >= 1
    int turn;

    int row;
    int column;

    ArrayList<String> path;

    Particle(int id, int turn) {
        this.id = id;
        this.turn = turn;

        row = 0;
        column = 0;

        path = new ArrayList<>();
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
