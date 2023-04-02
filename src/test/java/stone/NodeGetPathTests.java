package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class NodeGetPathTests {
    Maze maze = new Maze00();
    Tree tree = new Tree(maze);

    @Test
    public void should_return_empty_path_for_root_node() {
        // Arrange
        Node rootNode = new Node(2, 3, null, tree.ids);

        // Act
        String path = rootNode.getPath();

        // Assert
        assertThat(path).isEqualTo("");
    }

    @Test
    public void should_return_path_for_first_up_move() {
        // Arrange
        Node rootNode = new Node(1, 1, null, tree.ids);
        rootNode.addChildren(1, 0, 0, 0, tree.ids);

        Node endNode = rootNode.getUpNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("U");
    }

    @Test
    public void should_return_path_for_first_right_move() {
        // Arrange
        Node rootNode = new Node(1, 1, null, tree.ids);
        rootNode.addChildren(0, 1, 0, 0, tree.ids);

        Node endNode = rootNode.getRightNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("R");
    }

    @Test
    public void should_return_path_for_first_down_move() {
        // Arrange
        Node rootNode = new Node(1, 1, null, tree.ids);
        rootNode.addChildren(0, 0, 1, 0, tree.ids);

        Node endNode = rootNode.getDownNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("D");
    }

    @Test
    public void should_return_path_for_first_left_move() {
        // Arrange
        Node rootNode = new Node(1, 1, null, tree.ids);
        rootNode.addChildren(0, 0, 0, 1, tree.ids);

        Node endNode = rootNode.getLeftNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("L");
    }

    @Test
    public void should_return_path_for_right_right_down_movements() {
        // Arrange
        Node rootNode = new Node(0, 0, null, tree.ids);
        rootNode.addChildren(0, 1, 0, 0, tree.ids);

        Node firstNode = rootNode.getRightNode();
        firstNode.addChildren(0, 1, 0, 0, tree.ids);

        Node secondNode = firstNode.getRightNode();
        secondNode.addChildren(0, 0, 1, 0, tree.ids);

        Node endNode = secondNode.getDownNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("RRD");
    }

    @Test
    public void should_return_path_for_down_down_right_movements() {
        // Arrange
        Node rootNode = new Node(0, 0, null, tree.ids);
        rootNode.addChildren(0, 0, 1, 0, tree.ids);

        Node firstNode = rootNode.getDownNode();
        firstNode.addChildren(0, 0, 1, 0, tree.ids);

        Node secondNode = firstNode.getDownNode();
        secondNode.addChildren(0, 1, 0, 0, tree.ids);

        Node endNode = secondNode.getRightNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("DDR");
    }
}
