package stone;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int row;
    int column;

    Node parent;

    // Up, Right, Down, Left
    public ArrayList<Node> children;

    int distanceToEnd;

    public Node(int row, int column, Node parent) {
        this.row = row;
        this.column = column;
        this.parent = parent;
        children = new ArrayList<Node>();

        // TODO: Fix this. Static prop?
        int targetNodeRow = 64;
        int targetNodeColumn = 84;
        distanceToEnd = Math.abs(targetNodeRow - row) + Math.abs(targetNodeColumn - column);
    }

    public void addChildren(int up, int right, int down, int left) {
        Node upNode = (up == 1) ? new Node(row - 1, column, this) : null;
        Node rightNode = (right == 1) ? new Node(row, column + 1, this) : null;
        Node downNode = (down == 1) ? new Node(row + 1, column, this) : null;
        Node leftNode = (left == 1) ? new Node(row, column - 1, this) : null;

        children.add(upNode);
        children.add(rightNode);
        children.add(downNode);
        children.add(leftNode);
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
            result = result + path.get(i);
        }

        return result;
    }
}
