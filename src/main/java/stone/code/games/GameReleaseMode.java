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

import processing.core.PApplet;

public class GameReleaseMode {
    public static void run() {
        Maze maze = new Maze05Sinuca();

        Instant start = Instant.now();
        while (maze.particles.size() > 0) {
            maze.shift();
        }

        long duration = Duration.between(start, Instant.now()).toSeconds();

        System.out.println("Duration =" + duration + " s");

        ArrayList<String> output = new ArrayList<>();

        for (Particle p : maze.outParticles) {
            output.add(p.getFormatedPath());
        }

        String fileName = "src/main/java/stone/solutions/release/solutions_maze_" + maze.option + ".txt";
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
