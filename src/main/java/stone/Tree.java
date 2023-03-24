package stone;

import java.util.ArrayList;

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
}
