package stone;

import java.util.ArrayList;

public class Tree {
    Node root;

    int level;
    ArrayList<Node> levelNodes;

    ArrayList<Node> solutions;

    boolean showPathsOnMaze;

    public Tree(Maze maze) {
        root = new Node(maze.startCell.row, maze.startCell.column, null);

        level = 0;
        levelNodes = new ArrayList<Node>();
        levelNodes.add(root);

        solutions = new ArrayList<Node>();

        showPathsOnMaze = true;
    }

    public void goToNextLevel(ArrayList<Node> newNodes) {
        levelNodes = newNodes;
        level++;
    }

    public ArrayList<Node> getFilteredNodes() {
        ArrayList<Node> filteredNodes = new ArrayList<Node>();

        int maxRow = levelNodes.stream()
                .mapToInt(v -> v.row)
                .max().getAsInt();

        int maxColumn = levelNodes.stream()
                .mapToInt(v -> v.column)
                .max().getAsInt();

        for (int row = 0; row <= maxRow; row++) {
            for (int column = maxColumn; column >= 0; column--) {
                final int finalRow = row;
                final int finalColumn = column;
                Node node = levelNodes.stream()
                        .filter(n -> n.row == finalRow && n.column == finalColumn)
                        .findFirst()
                        .orElse(null);

                if (node == null) {
                    continue;
                }

                filteredNodes.add(node);
                column = -1;
            }
        }

        for (int column = 0; column <= maxColumn; column++) {
            for (int row = maxRow; row >= 0; row--) {
                final int finalRow = row;
                final int finalColumn = column;

                boolean nodeAlreadyFiltered = filteredNodes.stream()
                        .anyMatch(n -> n.row == finalRow && n.column == finalColumn);

                if (nodeAlreadyFiltered) {
                    row = -1;
                    continue;
                }

                Node node = levelNodes.stream()
                        .filter(n -> n.row == finalRow && n.column == finalColumn)
                        .findFirst()
                        .orElse(null);

                if (node == null) {
                    continue;
                }

                filteredNodes.add(node);
                row = -1;
            }
        }

        return filteredNodes;
    }

    public void goToNextLevel(Maze maze) {
        ArrayList<Node> newNodes = new ArrayList<Node>();
        ArrayList<Node> filteredNodes = getFilteredNodes();

        for (Node levelNode : filteredNodes) {
            int[] directions = maze.getNextDirections(levelNode.row, levelNode.column);
            levelNode.addChildren(directions[0], directions[1], directions[2], directions[3]);

            for (int i = 0; i < 4; i++) {
                Node node = levelNode.children.get(i);

                if (node == null) {
                    continue;
                }

                if (node.row == maze.endCell.row && node.column == maze.endCell.column) {
                    solutions.add(node);
                }

                newNodes.add(node);
            }
        }

        levelNodes = newNodes;
        level++;
    }

    public ArrayList<String> getSolutionsPaths() {
        ArrayList<String> paths = new ArrayList<String>();

        for (Node node : solutions) {
            paths.add(node.getPath());
        }

        return paths;
    }

    public void drawPathsOnMaze(Game game) {
        if (!showPathsOnMaze) {
            return;
        }

        game.fill(255, 255, 0);
        game.stroke(255, 255, 0);

        game.strokeWeight(2.50f);

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
