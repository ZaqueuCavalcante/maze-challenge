package stone.code.games;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import stone.code.Particle;
import stone.code.mazes.Maze;
import stone.code.mazes.Maze05Sinuca;

import java.util.Collections;
import java.util.HashMap;

import processing.core.PApplet;
import stone.code.Node;
import stone.code.cells.CellType;

public class GameReleaseMode {

    public static void run() {
        Maze maze = new Maze05Sinuca();

        Instant start = Instant.now();

        HashMap<Integer, String> output;

        while (maze.particles.size() > 0) {
            if (maze.turn % 100 == 0) {
                System.out.println("TURN = " + maze.turn + " -- PARTICLES = " + maze.particles.size());
            }

            maze.addParticle();

            if (maze.particleCanAccessEndCell) {
                for (Particle p : maze.particles.values()) {
                    p.tree.goToNextLevel(maze);
                    if (p.tree.solutions.size() > 0) {
                        maze.outParticlesIds.add(p.turn);
                    }
                }
            } else {
                maze.next[maze.endCell.row][maze.endCell.column] = CellType.OBSTACLE;
                for (Particle p : maze.particles.values()) {
                    p.tree.goToNextLevel(maze);
                    if (p.tree.solutions.size() > 0) {
                        maze.outParticlesIds.add(p.turn);
                    }
                }
                maze.next[maze.endCell.row][maze.endCell.column] = CellType.END;
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

                boolean isSolution = false;

                while (!isSolution) {
                    Maze filterMaze = new Maze05Sinuca();

                    ArrayList<String> paths = new ArrayList<>(output.values());

                    isSolution = filterMaze.isSolution(paths);

                    if (filterMaze.gameOverTurn != -1) {
                        output.remove(filterMaze.gameOverTurn);
                    }
                }

                String fileName = "src/main/java/stone/solutions/release/solutions_maze_" + maze.option + ".txt";
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

        long duration = Duration.between(start, Instant.now()).toSeconds();

        System.out.println("Duration =" + duration + " s");
    }
}
