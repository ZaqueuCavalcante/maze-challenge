package stone.code.games;

import java.io.File;
import java.io.FileNotFoundException;

import processing.core.PApplet;
import stone.code.mazes.Maze01Ton;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class GameReplayMode extends Game {
    public void settings() {
        maze = new Maze01Ton();

        int[] mazeSizes = maze.getDrawSizes();
        size(mazeSizes[0], mazeSizes[1]);
        CIZE = mazeSizes[2];

        File file = new File("src/main/java/stone/replays/replay_maze_" + maze.option + ".txt");
        InputStream input;

        try {
            input = new FileInputStream(file);

            String[] lines = PApplet.loadStrings(input);
            ArrayList<String> paths = new ArrayList<String>(Arrays.asList(lines));

            maze.setupReplay(paths);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);
        maze.draw(this);
    }

    public void keyPressed() {
        if (keyCode == 10) { // Enter
            maze.replay();
            maze.shift();
        }
    }
}
