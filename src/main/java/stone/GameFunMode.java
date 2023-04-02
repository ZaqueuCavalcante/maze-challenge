package stone;

public class GameFunMode extends Game {
    Player player;

    public void settings() {
        maze = new Maze01();

        player = new Player(maze.startCell.row, maze.startCell.column);
        player.updateMoveOptions(maze);

        int[] mazeSizes = maze.getDrawSizes();
        size(mazeSizes[0], mazeSizes[1]);
        CIZE = mazeSizes[2];
    }

    public void draw() {
        background(100);
        fill(255);
        stroke(0);
        maze.draw(this);
        player.draw(this);
    }

    public void keyPressed() {
        if (player.isDead || player.wonTheGame) {
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

            if (maze.currentIsObstacle(player.row, player.column)) {
                player.die();
            }

            if (maze.currentIsEnd(player.row, player.column)) {
                player.win();
            }
        }
    }

    private void updateMazeAndPlayer() {
        maze.shift();
        player.updateMoveOptions(maze);
    }
}
