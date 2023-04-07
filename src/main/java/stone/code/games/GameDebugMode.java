package stone.code.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import processing.core.PApplet;
import stone.code.Node;
import stone.code.Particle;
import stone.code.mazes.Maze;
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

    HashMap<Integer, String> output;

    public void keyPressed() {
        if (keyCode == 10) { // Enter

            maze.addParticle();

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

            maze.checkForCloseMaze();

            if (maze.particles.size() == 0) {
                output = new HashMap<>();

                Collections.sort(maze.outParticles, (a, b) -> Integer.compare(a.turn, b.turn));

                for (Particle p : maze.outParticles) {
                    Node node = p.tree.solutions.get(0);
                    String path = p.turn + " " + node.getPath();
                    output.put(p.turn, path);
                }

                filterPaths();

                String fileName = "src/main/java/stone/solutions/debug/solutions_maze_" + maze.option + ".txt";
                File file = new File(fileName);
                OutputStream outputStream;

                try {
                    outputStream = new FileOutputStream(file);
                    PApplet.saveStrings(outputStream, output.values().toArray(new String[0]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void filterPaths() {
        boolean isSolution = false;

        while (!isSolution) {
            Maze filterMaze = new Maze01Ton();

            ArrayList<String> paths = new ArrayList<>(output.values());

            isSolution = filterMaze.isSolution(paths);

            if (filterMaze.gameOverTurn != -1) {
                output.remove(filterMaze.gameOverTurn);
            }
        }
    }
}
