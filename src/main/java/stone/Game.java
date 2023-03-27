package stone;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Game extends PApplet {
    GameMode mode = GameMode.FUN;

    Maze maze = new Maze(MazeOption._02);
    Tree tree;

    int CIZE;

    int step;

    Player player;

    ArrayList<String> output = new ArrayList<String>();

    public void settings() {
        switch (maze.option) {
            case _00:
                size(600, 500);
                CIZE = 100;
                break;
            case _01:
                size(500, 450);
                CIZE = 50;
                break;
            case _02:
                size(1220, 940);
                CIZE = 14;
                break;
        }
    }

    public void setup() {
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
        textSize(CIZE / 2);
        textAlign(LEFT);
        text("GEN: " + step, CIZE, (float) (CIZE * 0.70));

        fill(255);
        stroke(0);

        maze.draw(this);

        if (mode == GameMode.FUN) {
            player.draw(this);
        }

        // tree.drawPathsOnMaze(this);
    }

    public void keyPressed() {
        if (keyCode == 10) { // Enter
            goToNextStep();

            if (output.size() > 0) {
                Collections.sort(output, (a, b) -> Integer.compare(a.length(), b.length()));

                saveStrings("src/main/java/stone/solutions/solutions_maze" + maze.option + ".txt",
                        output.toArray(new String[0]));
            }

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

                if (tree.levelNodes.size() > 500 && Math.random() < 0.5) {
                    continue;
                }

                int nodeRow = node.row;
                int nodeColumn = node.column;

                if (nodeRow == maze.endCell.row && nodeColumn == maze.endCell.column) {
                    output.add(node.getPath());
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
