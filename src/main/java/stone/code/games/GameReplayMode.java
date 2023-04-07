package stone.code.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import processing.core.PApplet;
import stone.code.Particle;
import stone.code.Tree;
import stone.code.mazes.Maze03Free10x10;

public class GameReplayMode extends Game {
    Tree tree;

    public void settings() {
        maze = new Maze03Free10x10();
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
            tree.goToNextLevel(maze);
            maze.shift();

            if (maze.particles.size() == 0) {
                ArrayList<String> output = new ArrayList<>();

                for (Particle p : maze.outParticles) {
                    output.add(p.getFormatedPath());
                }

                String fileName = "src/main/java/stone/solutions/debug/solutions_maze_" + maze.option + ".txt";
                File file = new File(fileName);
                OutputStream outputStream;

                try {
                    outputStream = new FileOutputStream(file);
                    PApplet.saveStrings(outputStream, output.toArray(new String[0]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
