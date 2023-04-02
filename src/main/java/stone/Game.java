package stone;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Game extends PApplet {
    GameMode mode = GameMode.RELEASE;

    int CIZE;

    Maze maze;
    Tree tree;

    Player player;

    public void settings() {
        maze = new Maze(MazeOption._03);
        tree = new Tree(maze);

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
                size(1305, 1010);
                CIZE = 15;
                break;
            case _03:
                size(2165, 1010);
                CIZE = 16;
                break;
        }
    }

    public void setup() {
        if (mode == GameMode.FUN) {
            player = new Player(maze.startCell.row, maze.startCell.column);
            player.updateMoveOptions(maze);
        }
    }

    public void draw() {
        if (mode == GameMode.RELEASE) {
            return;
        }

        setColors();

        maze.draw(this);

        if (mode == GameMode.FUN) {
            player.draw(this);
        }

        tree.drawPathsOnMaze(this);
    }

    private void setColors() {
        background(100);
        fill(255);
        stroke(0);
    }

    public void keyPressed() {
        if (mode == GameMode.FUN) {
            handlePlayerMove();
        }

        if (mode == GameMode.DEBUG) {
            handleDebugStep();
        }

        if (mode == GameMode.RELEASE) {
            handleReleaseSearch();
        }
    }

    private void handlePlayerMove() {
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

    private void handleDebugStep() {
        if (keyCode == 10) { // Enter
            goToNextStep();

            if (tree.solutions.size() > 0) {
                // tree.levelNodes = tree.solutions;

                ArrayList<String> output = tree.getSolutionsPaths();

                Collections.sort(output, (a, b) -> Integer.compare(a.length(), b.length()));

                String fileName = "src/main/java/stone/solutions/debug/solutions_maze" + maze.option + ".txt";
                saveStrings(fileName, output.toArray(new String[0]));

                System.out.println(output.size() + " paths | " + output.get(0).length() + " moves");
            }
        }
    }

    private void handleReleaseSearch() {
        if (keyCode == 10) { // Enter
            Instant start = Instant.now();

            while (tree.solutions.size() == 0) {
                goToNextStep();
            }

            ArrayList<String> output = tree.getSolutionsPaths();

            Collections.sort(output, (a, b) -> Integer.compare(a.length(), b.length()));

            String fileName = "src/main/java/stone/solutions/release/solutions_maze" + maze.option + ".txt";
            saveStrings(fileName, output.toArray(new String[0]));

            long duration = Duration.between(start, Instant.now()).toMillis();
            System.out.println(output.size() + " paths | " + duration + " ms | " + output.get(0).length() + " moves");
        }
    }

    public void goToNextStep() {
        tree.goToNextLevel(maze);
        maze.shift();
    }

    private void updateMazeAndPlayer() {
        maze.shift();
        player.updateMoveOptions(maze);
    }
}
