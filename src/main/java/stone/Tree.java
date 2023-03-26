package stone;

import java.util.ArrayList;

import processing.core.PConstants;

public class Tree {
    Node root;
    Node target;

    int level;
    ArrayList<Node> levelNodes;

    public Tree(Cell startCell, Cell endCell) {
        root = new Node(startCell.row, startCell.column, null);
        target = new Node(endCell.row, endCell.column, null);
        level = 0;
        levelNodes = new ArrayList<Node>();
        levelNodes.add(root);
    }

    public void draw(Game game) {
        game.translate((game.maze.columns + 5) * game.CIZE, 0);

        float x = 0;
        float y = 0;
        int size = 30;
        int size2 = size / 2;

        ArrayList<ArrayList<ZPoint>> points = new ArrayList<ArrayList<ZPoint>>();

        ArrayList<ZPoint> level_00 = new ArrayList<ZPoint>();
        level_00.add(new ZPoint(x, y));
        points.add(level_00);

        x = 0;
        y = y + 2 * size;
        ArrayList<ZPoint> level_01 = new ArrayList<ZPoint>();
        level_01.add(new ZPoint(x - 6 * size, y));
        level_01.add(new ZPoint(x - 2 * size, y));
        level_01.add(new ZPoint(x + 2 * size, y));
        level_01.add(new ZPoint(x + 6 * size, y));
        points.add(level_01);

        x = 0;
        y = y + 3 * size;
        ArrayList<ZPoint> level_02 = new ArrayList<ZPoint>();
        level_02.add(new ZPoint(x - 6 * size, y));
        level_02.add(new ZPoint(x - 2 * size, y));
        level_02.add(new ZPoint(x + 2 * size, y));
        level_02.add(new ZPoint(x + 6 * size, y));
        points.add(level_02);

        drawSquare(game, x - (size + size2), y, "U");
        drawSquare(game, x - size2, y, "R");
        drawSquare(game, x + size2, y, "D");
        drawSquare(game, x + (size + size2), y, "L");

        game.fill(255, 0, 0);

        for (ArrayList<ZPoint> level : points) {
            for (ZPoint point : level) {
                game.circle(point.x, point.y, 10);
            }
        }

        game.line(0, 0, 0, 800);

        game.rectMode(PConstants.CORNER);
    }

    public void drawSquare(Game game, float x, float y, String direction) {
        game.fill(200);
        game.stroke(0);
        game.rectMode(PConstants.CENTER);
        game.rect(x, y, 30, 30, 10);

        game.fill(0);
        game.textAlign(PConstants.CENTER, PConstants.CENTER);
        game.textSize(22);
        game.text(direction, x, y - 4);
    }

    public void drawPathsOnMaze(Game game) {
        // game.fill(148, 0, 211);
        // game.stroke(148, 0, 211);
        game.fill(255, 0, 0);
        game.stroke(255, 0, 0);

        game.strokeWeight(2);

        for (Node node : levelNodes) {
            float x1 = game.maze.startCell.column * game.CIZE + game.CIZE / 2;
            float y1 = game.maze.startCell.row * game.CIZE + game.CIZE / 2;
            float x2 = 0;
            float y2 = 0;

            String[] path = node.getPath().split("");
            if (path[0].equals("")) {
                x2 = x1;
                y2 = y1;
            }

            for (String direction : path) {
                if (direction.equals("U")) {
                    x2 = x1;
                    y2 = y1 - game.CIZE;
                }
                if (direction.equals("R")) {
                    x2 = x1 + game.CIZE;
                    y2 = y1;
                }
                if (direction.equals("D")) {
                    x2 = x1;
                    y2 = y1 + game.CIZE;
                }
                if (direction.equals("L")) {
                    x2 = x1 - game.CIZE;
                    y2 = y1;
                }

                game.line(x1, y1, x2, y2);

                x1 = x2;
                y1 = y2;
            }

            game.circle(x2, y2, (float) (game.CIZE * 0.40));
        }

        game.strokeWeight(1);
    }
}
