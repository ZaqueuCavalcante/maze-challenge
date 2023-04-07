package stone.code.games;

import stone.code.mazes.Maze03Free10x10;

public class GameDebugMode extends Game {
    public void settings() {
        maze = new Maze03Free10x10();

        int[] mazeSizes = maze.getDrawSizes();
        size(mazeSizes[0], mazeSizes[1]);
        CIZE = mazeSizes[2];
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);
        maze.draw(this);
    }

    public void keyPressed() {
        if (keyCode == 10) { // Enter
            maze.shift();
        }
    }
}
