package stone.code.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;
import stone.code.Node;
import stone.code.Particle;
import stone.code.mazes.Maze01Ton;

public class GameDebugMode extends Game {
    public void settings() {
        maze = new Maze01Ton();

        int[] mazeSizes = maze.getDrawSizes();
        size(mazeSizes[0], mazeSizes[1]);
        CIZE = mazeSizes[2];
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);
        maze.draw(this);

        for (Particle p : maze.particles.values()) {
            p.tree.drawPathsOnMaze(this);
        }
    }

    public void keyPressed() {
        if (keyCode == 10) { // Enter

            if (maze.turn == 1) {
                maze.addParticle();
            }

            for (Particle p : maze.particles.values()) {
                p.tree.goToNextLevel(maze);

                if (p.tree.solutions.size() > 0) {
                    maze.outParticlesIds.add(p.turn);
                }
            }

            for (int pId : maze.outParticlesIds) {
                Particle out = maze.particles.remove(pId);

                if (out != null) {
                    maze.outParticles.add(out);
                }
            }

            maze.shift();

            if (maze.particles.size() == 0) {
                ArrayList<String> output = new ArrayList<>();

                Collections.sort(maze.outParticles, (a, b) -> Integer.compare(a.turn, b.turn));

                for (Particle p : maze.outParticles) {
                    Node node = p.tree.solutions.get(1);
                    String path = p.turn + " " + node.getPath();
                    output.add(path);
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
