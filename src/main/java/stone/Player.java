package stone;

import java.util.ArrayList;

public class Player {
    int row;
    int column;

    boolean canMoveUp;
    boolean canMoveRight;
    boolean canMoveDown;
    boolean canMoveLeft;

    boolean isDead;
    boolean wonTheGame;

    ArrayList<String> path;

    public Player(int row, int column) {
        this.row = row;
        this.column = column;
        isDead = false;
        wonTheGame = false;
        path = new ArrayList<String>();
    }

    public void updateMoveOptions(int[][] nextMaze) {
        canMoveUp = (row - 1) >= 0 && nextMaze[row - 1][column] != CellType.OBSTACLE;
        canMoveRight = (column + 1) < nextMaze[0].length && nextMaze[row][column + 1] != CellType.OBSTACLE;
        canMoveDown = (row + 1) < nextMaze.length && nextMaze[row + 1][column] != CellType.OBSTACLE;
        canMoveLeft = (column - 1) >= 0 && nextMaze[row][column - 1] != CellType.OBSTACLE;
    }

    public void resetMoveOptions() {
        canMoveUp = false;
        canMoveRight = false;
        canMoveDown = false;
        canMoveLeft = false;
    }

    public void die() {
        isDead = true;
        resetMoveOptions();
    }

    public void win() {
        wonTheGame = true;
        resetMoveOptions();
    }

    public void up() {
        row--;
        path.add("U");
    }

    public void right() {
        column++;
        path.add("R");
    }

    public void down() {
        row++;
        path.add("D");
    }

    public void left() {
        column--;
        path.add("L");
    }

    public void draw(Game game) {
        drawSelf(game);

        drawMoveOptions(game);

        if (isDead || wonTheGame) {
            drawPath(game);
        }
    }

    private void drawSelf(Game game) {
        game.fill(255, 0, 0);
        game.circle(column * game.CIZE + game.CIZE / 2, row * game.CIZE + game.CIZE / 2, game.CIZE / 2);
    }

    private void drawMoveOptions(Game game) {
        if (canMoveUp) {
            game.circle((float) (column * game.CIZE + game.CIZE / 2), (float) (row * game.CIZE), game.CIZE / 4);
        }
        if (canMoveRight) {
            game.circle((float) (column * game.CIZE + game.CIZE), row * game.CIZE + game.CIZE / 2, game.CIZE / 4);
        }
        if (canMoveDown) {
            game.circle((float) (column * game.CIZE + game.CIZE / 2), (float) (row * game.CIZE + game.CIZE),
                    game.CIZE / 4);
        }
        if (canMoveLeft) {
            game.circle((float) (column * game.CIZE), row * game.CIZE + game.CIZE / 2, game.CIZE / 4);
        }
    }

    private void drawPath(Game game) {
        float x1 = game.maze.startCell.column * game.CIZE + game.CIZE / 2;
        float y1 = game.maze.startCell.row * game.CIZE + game.CIZE / 2;
        float x2 = 0;
        float y2 = 0;

        game.fill(255, 0, 0);
        game.stroke(255, 0, 0);
        game.strokeWeight(2);

        for (String direction : path) {
            if (direction == "U") {
                x2 = x1;
                y2 = y1 - game.CIZE;
            }
            if (direction == "R") {
                x2 = x1 + game.CIZE;
                y2 = y1;
            }
            if (direction == "D") {
                x2 = x1;
                y2 = y1 + game.CIZE;
            }
            if (direction == "L") {
                x2 = x1 - game.CIZE;
                y2 = y1;
            }

            game.line(x1, y1, x2, y2);

            x1 = x2;
            y1 = y2;
        }
        game.strokeWeight(1);
    }
}
