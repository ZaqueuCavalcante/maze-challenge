package stone;

import java.util.ArrayList;

public class Tree {
    Node root;

    int level;
    ArrayList<Node> levelNodes;

    boolean showPathsOnMaze;

    public Tree(Maze maze) {
        root = new Node(maze.startCell.row, maze.startCell.column, null, maze.endCell.row, maze.endCell.column);

        level = 0;
        levelNodes = new ArrayList<Node>();
        levelNodes.add(root);

        showPathsOnMaze = true;
    }

    public void goToNextLevel(ArrayList<Node> newNodes) {
        levelNodes = newNodes;
        level++;
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
