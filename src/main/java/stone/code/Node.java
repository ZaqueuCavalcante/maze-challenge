package stone.code;

import java.util.ArrayList;
import java.util.List;

import stone.code.cells.CellType;

public class Node {
    int row;
    int column;

    int id;

    Node parent;

    // Up, Right, Down, Left
    public ArrayList<Node> children;

    public Node(int row, int column, Node parent, int[][] ids) {
        this.row = row;
        this.column = column;
        this.parent = parent;
        children = new ArrayList<Node>();
        this.id = ids[row][column];
    }

    public void addChildren(int up, int right, int down, int left, int[][] ids) {
        Node upNode = (up == CellType.EMPTY) ? new Node(row - 1, column, this, ids) : null;
        Node rightNode = (right == CellType.EMPTY) ? new Node(row, column + 1, this, ids) : null;
        Node downNode = (down == CellType.EMPTY) ? new Node(row + 1, column, this, ids) : null;
        Node leftNode = (left == CellType.EMPTY) ? new Node(row, column - 1, this, ids) : null;

        children.add(upNode);
        children.add(rightNode);
        children.add(downNode);
        children.add(leftNode);
    }

    @Override
    public boolean equals(Object other) {
        Node node = (Node) other;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Node getUpNode() {
        return children.get(0);
    }

    public Node getRightNode() {
        return children.get(1);
    }

    public Node getDownNode() {
        return children.get(2);
    }

    public Node getLeftNode() {
        return children.get(3);
    }

    public String getPath() {
        if (parent == null) {
            return "";
        }

        List<String> path = new ArrayList<String>();
        Node auxParent = parent;

        int currentRow = this.row;
        int currentColumn = this.column;
        int parentRow = parent.row;
        int parentColumn = parent.column;

        while (auxParent != null) {
            if (parentRow < currentRow) {
                path.add("D");
            }
            if (parentColumn > currentColumn) {
                path.add("L");
            }
            if (parentRow > currentRow) {
                path.add("U");
            }
            if (parentColumn < currentColumn) {
                path.add("R");
            }

            currentRow = parentRow;
            currentColumn = parentColumn;
            auxParent = auxParent.parent;
            if (auxParent == null) {
                continue;
            }
            parentRow = auxParent.row;
            parentColumn = auxParent.column;
        }

        String result = "";

        for (int i = path.size() - 1; i >= 0; i--) {
            result = result + path.get(i) + " ";
        }

        result = result.trim();

        return result;
    }
}
