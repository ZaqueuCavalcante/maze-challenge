package stone;

import java.util.ArrayList;
import java.util.Collections;

public class GameDebugMode extends Game {
    Tree tree;

    public void settings() {
        maze = new Maze02();
        tree = new Tree(maze);

        int[] mazeSizes = maze.getDrawSizes();
        size(mazeSizes[0], mazeSizes[1]);
        CIZE = mazeSizes[2];
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);
        maze.draw(this);
        tree.drawPathsOnMaze(this);
    }

    public void keyPressed() {
        if (keyCode == 10) { // Enter
            goToNextStep();

            if (tree.solutions.size() > 0) {
                ArrayList<String> output = tree.getSolutionsPaths();

                Collections.sort(output, (a, b) -> Integer.compare(a.length(), b.length()));

                String fileName = "src/main/java/stone/solutions/debug/solutions_maze_" + maze.option + ".txt";
                saveStrings(fileName, output.toArray(new String[0]));

                System.out.println(output.size() + " paths | " + output.get(0).length() + " moves");
            }
        }
    }

    public void goToNextStep() {
        tree.goToNextLevel(maze);
        maze.shift();
    }
}
