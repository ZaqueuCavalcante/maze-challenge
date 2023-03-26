package stone;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import processing.core.PApplet;

public class Game extends PApplet {
    GameMode mode;

    Maze maze;
    Tree tree;

    int CIZE = 15;

    int step;

    Player player;

    public void settings() {
        // size(260, 200);
        size(1600, 1100);
    }

    public void setup() {
        mode = GameMode.DEBUG;

        maze = new Maze(MazeOption._02);
        maze.showNeighbors = false;

        tree = new Tree(maze.startCell, maze.endCell);
        tree.root.addChildren(0, 0, 1, 0);

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
        stroke(255);
        textSize(20);
        textAlign(LEFT);
        text("STEP: " + step, 25, 50);

        fill(255);
        stroke(0);

        maze.draw(this);

        if (mode == GameMode.FUN) {
            player.draw(this);
        }

        tree.drawPathsOnMaze(this);

        // tree.draw(this);
    }

    boolean x = true;

    public void keyPressed() {
        if (keyCode == 10) { // Enter
            goToNextStep();

            // Instant starts = Instant.now();

            // while (x) {
            // goToNextStep();
            // }

            // Instant ends = Instant.now();
            // System.out.println(Duration.between(starts, ends).toMillis());
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

    public void goToNextStep() {
        step++;
        maze.shift();

        ArrayList<Node> newNodes = new ArrayList<Node>();
        System.out.println("LevelNodesSize = " + tree.levelNodes.size());

        if (tree.levelNodes.size() == 0) {
            System.out.println("FUDEU");
        }

        int minDistanceToTarget = tree.levelNodes.stream()
                .mapToInt(v -> v.distanceToEnd)
                .min().getAsInt() + 5;

        for (Node levelNode : tree.levelNodes) {
            for (int i = 0; i < 4; i++) {
                Node node = levelNode.children.get(i);

                if (node == null) {
                    continue;
                }

                if (node.distanceToEnd > minDistanceToTarget) {
                    continue;
                }

                if (tree.levelNodes.size() > 100 && Math.random() < 0.5) {
                    continue;
                }

                int nodeRow = node.row;
                int nodeColumn = node.column;

                if (nodeRow == maze.endCell.row && nodeColumn == maze.endCell.column) {
                    x = false;

                    // Save output on file

                    return;
                    // String path = node.getPath();
                    // System.out.println(path);
                }

                int up = ((nodeRow - 1) >= 0 && maze.next[nodeRow - 1][nodeColumn] != 1) ? 1 : 0;
                int right = ((nodeColumn + 1) < maze.columns && maze.next[nodeRow][nodeColumn + 1] != 1) ? 1 : 0;
                int down = ((nodeRow + 1) < maze.rows && maze.next[nodeRow + 1][nodeColumn] != 1) ? 1 : 0;
                int left = ((nodeColumn - 1) >= 0 && maze.next[nodeRow][nodeColumn - 1] != 1) ? 1 : 0;

                node.addChildren(up, right, down, left);

                newNodes.add(node);
            }
        }

        tree.levelNodes = newNodes;
        tree.level++;
    }

    public void updateMazeAndPlayer() {
        step++;
        maze.shift();
        player.updateMoveOptions(maze.next);
    }
}
