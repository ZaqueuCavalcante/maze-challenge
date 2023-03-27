package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class NodeGetPathTests {
    @Test
    public void should_return_empty_path_for_root_node() {
        // Arrange
        Node rootNode = new Node(2, 3, null, 4, 5);

        // Act
        String path = rootNode.getPath();

        // Assert
        assertThat(path).isEqualTo("");
    }

    @Test
    public void should_return_path_for_first_up_move() {
        // Arrange
        Node rootNode = new Node(1, 1, null, 4, 5);
        rootNode.addChildren(1, 0, 0, 0);

        Node endNode = rootNode.getUpNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("U");
    }

    @Test
    public void should_return_path_for_first_right_move() {
        // Arrange
        Node rootNode = new Node(1, 1, null, 4, 5);
        rootNode.addChildren(0, 1, 0, 0);

        Node endNode = rootNode.getRightNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("R");
    }

    @Test
    public void should_return_path_for_first_down_move() {
        // Arrange
        Node rootNode = new Node(1, 1, null, 4, 5);
        rootNode.addChildren(0, 0, 1, 0);

        Node endNode = rootNode.getDownNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("D");
    }

    @Test
    public void should_return_path_for_first_left_move() {
        // Arrange
        Node rootNode = new Node(1, 1, null, 4, 5);
        rootNode.addChildren(0, 0, 0, 1);

        Node endNode = rootNode.getLeftNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("L");
    }

    @Test
    public void should_return_path_for_right_right_down_movements() {
        // Arrange
        Node rootNode = new Node(0, 0, null, 4, 5);
        rootNode.addChildren(0, 1, 0, 0);

        Node firstNode = rootNode.getRightNode();
        firstNode.addChildren(0, 1, 0, 0);

        Node secondNode = firstNode.getRightNode();
        secondNode.addChildren(0, 0, 1, 0);

        Node endNode = secondNode.getDownNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("RRD");
    }

    @Test
    public void should_return_path_for_down_down_right_movements() {
        // Arrange
        Node rootNode = new Node(0, 0, null, 4, 5);
        rootNode.addChildren(0, 0, 1, 0);

        Node firstNode = rootNode.getDownNode();
        firstNode.addChildren(0, 0, 1, 0);

        Node secondNode = firstNode.getDownNode();
        secondNode.addChildren(0, 1, 0, 0);

        Node endNode = secondNode.getRightNode();

        // Act
        String path = endNode.getPath();

        // Assert
        assertThat(path).isEqualTo("DDR");
    }
}
