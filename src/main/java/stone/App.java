package stone;

import java.time.Duration;
import java.time.Instant;

public class App {
    public static void main(String[] args) {
        Maze maze = new Maze(MazeOption._03);
        Tree tree = new Tree(maze);

        Instant start = Instant.now();

        while (tree.solutions.size() == 0) {
            tree.goToNextLevel(maze);
            maze.shift();
        }

        long duration = Duration.between(start, Instant.now()).toMillis();

        System.out.println(duration + " ms | ");
        System.out.println(tree.getSolutionsPaths().get(0).length());
    }

    // public static void main(String[] args) {
    // String[] appletArgs = new String[] { "stone.Game" };

    // PApplet.main(appletArgs);
    // }
}
