package stone;

import java.util.ArrayList;

public class Player {
    int row;
    int column;

    boolean canMoveUp;
    boolean canMoveRight;
    boolean canMoveDown;
    boolean canMoveLeft;

    boolean canMoveUpRight;
    boolean canMoveRightDown;
    boolean canMoveDownLeft;
    boolean canMoveLeftUp;

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

    public void updateMoveOptions(Maze maze) {
        int[] directions = maze.getNextDirections(row, column);
        canMoveUp = directions[0] == 1;
        canMoveUpRight = directions[1] == 1;
        canMoveRight = directions[2] == 1;
        canMoveRightDown = directions[3] == 1;
        canMoveDown = directions[4] == 1;
        canMoveDownLeft = directions[5] == 1;
        canMoveLeft = directions[6] == 1;
        canMoveLeftUp = directions[7] == 1;
    }

    public void resetMoveOptions() {
        canMoveUp = false;
        canMoveRight = false;
        canMoveDown = false;
        canMoveLeft = false;

        canMoveUpRight = false;
        canMoveRightDown = false;
        canMoveDownLeft = false;
        canMoveLeftUp = false;
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
        path.add("8");
    }

    public void upRight() {
        row--;
        column++;
        path.add("9");
    }

    public void right() {
        column++;
        path.add("6");
    }

    public void rightDown() {
        column++;
        row++;
        path.add("3");
    }

    public void down() {
        row++;
        path.add("2");
    }

    public void downLeft() {
        row++;
        column--;
        path.add("1");
    }

    public void left() {
        column--;
        path.add("4");
    }

    public void leftUp() {
        column--;
        row--;
        path.add("7");
    }

    public void draw(Game game) {
        if (isDead || wonTheGame) {
            drawPath(game);
        }

        drawSelf(game);

        drawMoveOptions(game);
    }

    private void drawSelf(Game game) {
        game.fill(255, 0, 0);
        game.circle(column * game.CIZE + game.CIZE / 2, row * game.CIZE + game.CIZE / 2, game.CIZE / 2);
    }

    private void drawMoveOptions(Game game) {
        float r = game.CIZE / 2;
        float x = (float) (column * game.CIZE + r);
        float y = row * game.CIZE + r;

        if (canMoveUp) {
            game.circle(x, y - r, r / 2);
        }
        if (canMoveUpRight) {
            game.circle(x + r, y - r, r / 2);
        }
        if (canMoveRight) {
            game.circle(x + r, y, r / 2);
        }
        if (canMoveRightDown) {
            game.circle(x + r, y + r, r / 2);
        }
        if (canMoveDown) {
            game.circle(x, y + r, r / 2);
        }
        if (canMoveDownLeft) {
            game.circle(x - r, y + r, r / 2);
        }
        if (canMoveLeft) {
            game.circle(x - r, y, r / 2);
        }
        if (canMoveLeftUp) {
            game.circle(x - r, y - r, r / 2);
        }
    }

    private void drawPath(Game game) {
        float x1 = game.maze.startCell.column * game.CIZE + game.CIZE / 2;
        float y1 = game.maze.startCell.row * game.CIZE + game.CIZE / 2;
        float x2 = 0;
        float y2 = 0;

        game.fill(148, 0, 211);
        game.stroke(148, 0, 211);
        game.strokeWeight(2);

        for (String direction : path) {
            if (direction.equals("8")) {
                x2 = x1;
                y2 = y1 - game.CIZE;
            }
            if (direction.equals("6")) {
                x2 = x1 + game.CIZE;
                y2 = y1;
            }
            if (direction.equals("2")) {
                x2 = x1;
                y2 = y1 + game.CIZE;
            }
            if (direction.equals("4")) {
                x2 = x1 - game.CIZE;
                y2 = y1;
            }

            if (direction.equals("9")) {
                x2 = x1 + game.CIZE;
                y2 = y1 - game.CIZE;
            }
            if (direction.equals("3")) {
                x2 = x1 + game.CIZE;
                y2 = y1 + game.CIZE;
            }
            if (direction.equals("1")) {
                x2 = x1 - game.CIZE;
                y2 = y1 + game.CIZE;
            }
            if (direction.equals("7")) {
                x2 = x1 - game.CIZE;
                y2 = y1 - game.CIZE;
            }

            game.line(x1, y1, x2, y2);

            x1 = x2;
            y1 = y2;
        }
        game.strokeWeight(1);
    }
}
