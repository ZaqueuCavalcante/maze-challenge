package stone;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Game extends PApplet {
    GameMode mode = GameMode.RELEASE;

    int step;
    int CIZE;

    Maze maze;
    Tree tree;

    Player player;

    ArrayList<String> output;

    public void settings() {
        maze = new Maze(MazeOption._02);

        if (mode == GameMode.RELEASE) {
            size(500, 500);
            return;
        }

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
            case _03:
                size(1760, 830);
                CIZE = 13;
                break;
        }
    }

    public void setup() {
        step = 0;

        if (mode == GameMode.FUN) {
            player = new Player(maze.startCell.row, maze.startCell.column);
            player.updateMoveOptions(maze);
        }

        tree = new Tree(maze);

        output = new ArrayList<String>();
    }

    public void draw() {
        if (mode == GameMode.RELEASE) {
            return;
        }

        drawGenerationCounter();

        maze.draw(this);

        if (mode == GameMode.FUN) {
            player.draw(this);
        }

        tree.drawPathsOnMaze(this);
    }

    private void drawGenerationCounter() {
        background(100);

        fill(255);
        stroke(255);
        textSize(CIZE / 2);
        textAlign(LEFT);
        text("GEN: " + step, CIZE, (float) (CIZE * 0.70));

        fill(255);
        stroke(0);
    }

    public void keyPressed() {
        if (keyCode == 10) { // Enter
            if (mode == GameMode.DEBUG) {
                goToNextStep();

                if (output.size() > 0) {
                    Collections.sort(output, (a, b) -> Integer.compare(a.length(), b.length()));

                    saveStrings("src/main/java/stone/solutions/debug/solutions_maze" + maze.option + ".txt",
                            output.toArray(new String[0]));

                    System.out.println("Found " + output.size() + " paths");
                }
            }

            if (mode == GameMode.RELEASE) {
                Instant starts = Instant.now();

                while (output.size() == 0) {
                    goToNextStep();
                }

                Collections.sort(output, (a, b) -> Integer.compare(a.length(), b.length()));

                saveStrings("src/main/java/stone/solutions/release/solutions_maze" + maze.option +
                        ".txt",
                        output.toArray(new String[0]));

                Instant ends = Instant.now();
                System.out
                        .println("Found " + output.size() + " paths in " + Duration.between(starts, ends).toMillis()
                                + " milliseconds" + " and bestPathLength = " + output.get(0).length());

                settings();
                setup();
                return;
            }
        }

        if (mode == GameMode.FUN) {
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
    }

    public void goToNextStep() {
        ArrayList<Node> newNodes = new ArrayList<Node>();

        if (tree.levelNodes.size() == 0) {
            System.out.println("NOT FOUND");
            return;
        }

        int minDistanceToEnd = tree.levelNodes.stream()
                .mapToInt(v -> v.distanceToEnd)
                .min().getAsInt();

        long totalNodesMinDistance = tree.levelNodes.stream()
                .filter(v -> v.distanceToEnd == minDistanceToEnd)
                .count();

        int maxRow = tree.levelNodes.stream()
                .mapToInt(v -> v.row)
                .max().getAsInt();

        int maxColumn = tree.levelNodes.stream()
                .mapToInt(v -> v.column)
                .max().getAsInt();

        // System.out.println("TotalLevelNodes = " + tree.levelNodes.size());
        // System.out.println("MinDistanceToEnd = " + minDistanceToEnd);
        // System.out.println("TotalNodesMinDistance = " + totalNodesMinDistance);
        // System.out.println("MaxRow = " + maxRow);
        // System.out.println("MaxColumn = " + maxColumn);

        ArrayList<Node> filteredNodes = new ArrayList<Node>();
        for (int row = 0; row <= maxRow; row++) {
            for (int column = maxColumn; column >= 0; column--) {
                final int finalRow = row;
                final int finalColumn = column;
                Node node = tree.levelNodes.stream()
                        .filter(n -> n.row == finalRow && n.column == finalColumn)
                        .findFirst()
                        .orElse(null);

                if (node == null) {
                    continue;
                }

                filteredNodes.add(node);
                // column = -1;
            }
        }

        // System.out.println("TotalFilteredNodes = " + filteredNodes.size());
        // System.out.println("------------------------");

        for (Node levelNode : filteredNodes) {

            int[] directions = maze.getNextDirections(levelNode.row, levelNode.column);
            int up = directions[0];
            int right = directions[1];
            int down = directions[2];
            int left = directions[3];
            levelNode.addChildren(up, right, down, left);

            for (int i = 0; i < 4; i++) {
                Node node = levelNode.children.get(i);

                if (node == null) {
                    continue;
                }

                int nodeRow = node.row;
                int nodeColumn = node.column;

                if (nodeRow == maze.endCell.row && nodeColumn == maze.endCell.column) {
                    output.add(node.getPath());
                }

                newNodes.add(node);
            }
        }

        tree.goToNextLevel(newNodes);

        step++;
        maze.shift();
    }

    public void updateMazeAndPlayer() {
        step++;
        maze.shift();
        player.updateMoveOptions(maze);
    }
}
