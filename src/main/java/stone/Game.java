package stone;

import processing.core.PApplet;

public class Game extends PApplet {

    GameMode mode;

    Maze maze;

    int ROWS;
    int COLUMNS;
    int CELL_SIZE = 48;

    int step;

    public void settings() {
        size(1200, 800);
    }

    public void setup() {
        mode = GameMode.FUN;

        maze = new Maze(MazeOption._01);

        ROWS = 7;
        COLUMNS = 8;

        step = 0;
    }

    public void draw() {
        background(100);
        fill(255);

        float pageTwoX = (float) (COLUMNS + 2.50) * CELL_SIZE;

        stroke(255);
        line(pageTwoX, 25, pageTwoX, 700);

        textSize(20);
        textAlign(LEFT);

        text("STEP: " + step, pageTwoX + 10, (float) (CELL_SIZE));
    }

    public void keyPressed() {
        if (keyCode == 10) { // Enter
            step++;
        }
    }
}
