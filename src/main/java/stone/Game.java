package stone;

import processing.core.PApplet;

public class Game extends PApplet {

    GameMode mode;

    Maze maze;

    int CELL_SIZE = 48;

    int step;

    Player player;

    public void settings() {
        size(1400, 800);
    }

    public void setup() {
        mode = GameMode.FUN;

        maze = new Maze(MazeOption._01);

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

        translate(CELL_SIZE, CELL_SIZE);
        for (int row = 0; row < maze.rows; row++) {
            for (int column = 0; column < maze.columns; column++) {
                if (maze.current[row][column] == CellType.EMPTY) {
                    fill(200);
                }
                if (maze.current[row][column] == CellType.OBSTACLE) {
                    fill(0, 128, 0);
                }
                if (maze.current[row][column] == CellType.START || maze.current[row][column] == CellType.END) {
                    fill(255, 255, 0);
                }

                rect(column * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE, CELL_SIZE /
                        4);
            }
        }

        // Player
        fill(255, 0, 0);
        circle(player.column * CELL_SIZE + CELL_SIZE / 2, player.row * CELL_SIZE + CELL_SIZE / 2, CELL_SIZE / 2);

        if (player.canMoveUp) {
            circle((float) (player.column * CELL_SIZE + CELL_SIZE / 2),
                    (float) (player.row * CELL_SIZE), CELL_SIZE / 4);
        }
        if (player.canMoveRight) {
            circle((float) (player.column * CELL_SIZE + CELL_SIZE), player.row * CELL_SIZE + CELL_SIZE / 2,
                    CELL_SIZE / 4);
        }
        if (player.canMoveDown) {
            circle((float) (player.column * CELL_SIZE + CELL_SIZE / 2),
                    (float) (player.row * CELL_SIZE + CELL_SIZE), CELL_SIZE / 4);
        }
        if (player.canMoveLeft) {
            circle((float) (player.column * CELL_SIZE), player.row * CELL_SIZE + CELL_SIZE / 2,
                    CELL_SIZE / 4);
        }

        if (player.isDead || player.wonTheGame) {
            int row = maze.startCell.row;
            int column = maze.startCell.column;

            float x1 = column * CELL_SIZE + CELL_SIZE / 2;
            float y1 = row * CELL_SIZE + CELL_SIZE / 2;
            float x2 = 0;
            float y2 = 0;

            fill(255, 0, 0);
            stroke(255, 0, 0);
            strokeWeight(4);

            for (String direction : player.path) {
                if (direction == "U") {
                    x2 = x1;
                    y2 = y1 - CELL_SIZE;
                }
                if (direction == "R") {
                    x2 = x1 + CELL_SIZE;
                    y2 = y1;
                }
                if (direction == "D") {
                    x2 = x1;
                    y2 = y1 + CELL_SIZE;
                }
                if (direction == "L") {
                    x2 = x1 - CELL_SIZE;
                    y2 = y1;
                }

                line(x1, y1, x2, y2);

                x1 = x2;
                y1 = y2;
            }
            strokeWeight(1);
        }
        // Player

        translate((float) maze.columns * CELL_SIZE + CELL_SIZE / 2, 0);
        fill(255);
        stroke(255);
        line(0, 0, 0, maze.rows * CELL_SIZE);

        translate(CELL_SIZE / 2, 0);
        textSize(20);
        textAlign(LEFT);
        text("STEP: " + step, 0, 25);

        float y = 50;
        text("PATH: ", 0, y);
        int directionCount = 0;
        for (String direction : player.path) {
            float x = (float) 1.40 * CELL_SIZE + directionCount * CELL_SIZE / 2;
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

                // TODO: Improve encapsulation here
                if (keyCode == 38 && player.row > 0) {
                    player.row--;
                    player.path.add("U");
                    updateMazeAndPlayer();
                }
                if (keyCode == 39 && player.column < maze.columns - 1) {
                    player.column++;
                    player.path.add("R");
                    updateMazeAndPlayer();
                }
                if (keyCode == 40 && player.row < maze.rows - 1) {
                    player.row++;
                    player.path.add("D");
                    updateMazeAndPlayer();
                }
                if (keyCode == 37 && player.column > 0) {
                    player.column--;
                    player.path.add("L");
                    updateMazeAndPlayer();
                }

                if (maze.current[player.row][player.column] == CellType.OBSTACLE) {
                    player.isDead = true;
                    player.resetMoveOptions();
                    player.path.add("X");
                }

                if (maze.current[player.row][player.column] == CellType.END) {
                    player.wonTheGame = true;
                    player.resetMoveOptions();
                    player.path.add("V");
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
