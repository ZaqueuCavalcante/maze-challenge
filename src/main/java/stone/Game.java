package stone;

import processing.core.PApplet;

public class Game extends PApplet {
    GameMode mode;

    Maze maze;

    int CIZE = 12;

    int step;

    Player player;

    public void settings() {
        size(1400, 800);
    }

    public void setup() {
        mode = GameMode.FUN;

        maze = new Maze(MazeOption._02);

        step = 0;

        switch (mode) {
            case FUN:
                player = new Player(maze.startCell.row, maze.startCell.column);
                player.updateMoveOptions(maze.next);
                break;
            case DEBUG:
                break;
            case RELEASE:
                break;
        }
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);

        maze.draw(this);

        player.draw(this);

        translate((float) maze.columns * CIZE + CIZE / 2, 0);
        fill(255);
        stroke(255);
        line(0, 0, 0, maze.rows * CIZE);

        translate(CIZE / 2, 0);
        textSize(20);
        textAlign(LEFT);
        text("STEP: " + step, 0, 25);

        float y = 50;
        text("PATH: ", 0, y);
        int directionCount = 0;
        for (String direction : player.path) {
            float x = (float) 1.40 * CIZE + directionCount * CIZE / 2;
            text(direction + " ", x, y);
            directionCount++;
        }
    }

    public void keyPressed() {
        if (keyCode == 10) { // Enter
            step++;
        }

        if (mode == GameMode.FUN) {
            if (player.isDead) {
                return;
            }

            if (keyCode >= 37 && keyCode <= 40) {

                if (keyCode == 38 && player.row > 0) {
                    player.up();
                    updateMazeAndPlayer();
                }
                if (keyCode == 39 && player.column < maze.columns - 1) {
                    player.right();
                    updateMazeAndPlayer();
                }
                if (keyCode == 40 && player.row < maze.rows - 1) {
                    player.down();
                    updateMazeAndPlayer();
                }
                if (keyCode == 37 && player.column > 0) {
                    player.left();
                    updateMazeAndPlayer();
                }

                if (maze.isObstacle(player.row, player.column)) {
                    player.die();
                }

                if (maze.isEnd(player.row, player.column)) {
                    player.win();
                }
            }
        }
    }

    public void updateMazeAndPlayer() {
        step++;
        maze.shift();
        player.updateMoveOptions(maze.next);
    }
}
