package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class NodeAddChildrenTests {
    @Test
    public void should_create_a_root_node() {
        // Arrange
        int row = 2;
        int column = 3;

        // Act
        Node rootNode = new Node(row, column, null);

        // Assert
        assertThat(rootNode.row).isEqualTo(row);
        assertThat(rootNode.column).isEqualTo(column);
        assertThat(rootNode.parent).isNull();
        assertThat(rootNode.children).hasSize(0);
    }

    @Test
    public void should_create_a_root_node_without_children() {
        // Arrange
        int up = 0;
        int right = 0;
        int down = 0;
        int left = 0;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNull();
        assertThat(rootNode.children.get(1)).isNull();
        assertThat(rootNode.children.get(2)).isNull();
        assertThat(rootNode.children.get(3)).isNull();
    }

    @Test
    public void should_create_a_root_node_with_only_up_child() {
        // Arrange
        int up = 1;
        int right = 0;
        int down = 0;
        int left = 0;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNotNull();
        assertThat(rootNode.children.get(1)).isNull();
        assertThat(rootNode.children.get(2)).isNull();
        assertThat(rootNode.children.get(3)).isNull();

        assertThat(rootNode.children.get(0).row).isEqualTo(rootNode.row - 1);
        assertThat(rootNode.children.get(0).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(0).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_only_right_child() {
        // Arrange
        int up = 0;
        int right = 1;
        int down = 0;
        int left = 0;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNull();
        assertThat(rootNode.children.get(1)).isNotNull();
        assertThat(rootNode.children.get(2)).isNull();
        assertThat(rootNode.children.get(3)).isNull();

        assertThat(rootNode.children.get(1).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(1).column).isEqualTo(rootNode.column + 1);
        assertThat(rootNode.children.get(1).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_only_down_child() {
        // Arrange
        int up = 0;
        int right = 0;
        int down = 1;
        int left = 0;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNull();
        assertThat(rootNode.children.get(1)).isNull();
        assertThat(rootNode.children.get(2)).isNotNull();
        assertThat(rootNode.children.get(3)).isNull();

        assertThat(rootNode.children.get(2).row).isEqualTo(rootNode.row + 1);
        assertThat(rootNode.children.get(2).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(2).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_only_left_child() {
        // Arrange
        int up = 0;
        int right = 0;
        int down = 0;
        int left = 1;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNull();
        assertThat(rootNode.children.get(1)).isNull();
        assertThat(rootNode.children.get(2)).isNull();
        assertThat(rootNode.children.get(3)).isNotNull();

        assertThat(rootNode.children.get(3).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(3).column).isEqualTo(rootNode.column - 1);
        assertThat(rootNode.children.get(3).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_up_and_right_children() {
        // Arrange
        int up = 1;
        int right = 1;
        int down = 0;
        int left = 0;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNotNull();
        assertThat(rootNode.children.get(1)).isNotNull();
        assertThat(rootNode.children.get(2)).isNull();
        assertThat(rootNode.children.get(3)).isNull();

        assertThat(rootNode.children.get(0).row).isEqualTo(rootNode.row - 1);
        assertThat(rootNode.children.get(0).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(0).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(1).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(1).column).isEqualTo(rootNode.column + 1);
        assertThat(rootNode.children.get(1).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_up_and_down_children() {
        // Arrange
        int up = 1;
        int right = 0;
        int down = 1;
        int left = 0;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNotNull();
        assertThat(rootNode.children.get(1)).isNull();
        assertThat(rootNode.children.get(2)).isNotNull();
        assertThat(rootNode.children.get(3)).isNull();

        assertThat(rootNode.children.get(0).row).isEqualTo(rootNode.row - 1);
        assertThat(rootNode.children.get(0).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(0).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(2).row).isEqualTo(rootNode.row + 1);
        assertThat(rootNode.children.get(2).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(2).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_up_and_left_children() {
        // Arrange
        int up = 1;
        int right = 0;
        int down = 0;
        int left = 1;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNotNull();
        assertThat(rootNode.children.get(1)).isNull();
        assertThat(rootNode.children.get(2)).isNull();
        assertThat(rootNode.children.get(3)).isNotNull();

        assertThat(rootNode.children.get(0).row).isEqualTo(rootNode.row - 1);
        assertThat(rootNode.children.get(0).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(0).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(3).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(3).column).isEqualTo(rootNode.column - 1);
        assertThat(rootNode.children.get(3).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_up_and_right_and_down_children() {
        // Arrange
        int up = 1;
        int right = 1;
        int down = 1;
        int left = 0;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNotNull();
        assertThat(rootNode.children.get(1)).isNotNull();
        assertThat(rootNode.children.get(2)).isNotNull();
        assertThat(rootNode.children.get(3)).isNull();

        assertThat(rootNode.children.get(0).row).isEqualTo(rootNode.row - 1);
        assertThat(rootNode.children.get(0).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(0).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(1).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(1).column).isEqualTo(rootNode.column + 1);
        assertThat(rootNode.children.get(1).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(2).row).isEqualTo(rootNode.row + 1);
        assertThat(rootNode.children.get(2).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(2).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_up_and_right_and_left_children() {
        // Arrange
        int up = 1;
        int right = 1;
        int down = 0;
        int left = 1;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNotNull();
        assertThat(rootNode.children.get(1)).isNotNull();
        assertThat(rootNode.children.get(2)).isNull();
        assertThat(rootNode.children.get(3)).isNotNull();

        assertThat(rootNode.children.get(0).row).isEqualTo(rootNode.row - 1);
        assertThat(rootNode.children.get(0).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(0).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(1).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(1).column).isEqualTo(rootNode.column + 1);
        assertThat(rootNode.children.get(1).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(3).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(3).column).isEqualTo(rootNode.column - 1);
        assertThat(rootNode.children.get(3).parent).isEqualTo(rootNode);
    }

    @Test
    public void should_create_a_root_node_with_all_children() {
        // Arrange
        int up = 1;
        int right = 1;
        int down = 1;
        int left = 1;

        // Act
        Node rootNode = new Node(2, 3, null);
        rootNode.addChildren(up, right, down, left);

        // Assert
        assertThat(rootNode.children).hasSize(4);
        assertThat(rootNode.children.get(0)).isNotNull();
        assertThat(rootNode.children.get(1)).isNotNull();
        assertThat(rootNode.children.get(2)).isNotNull();
        assertThat(rootNode.children.get(3)).isNotNull();

        assertThat(rootNode.children.get(0).row).isEqualTo(rootNode.row - 1);
        assertThat(rootNode.children.get(0).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(0).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(1).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(1).column).isEqualTo(rootNode.column + 1);
        assertThat(rootNode.children.get(1).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(2).row).isEqualTo(rootNode.row + 1);
        assertThat(rootNode.children.get(2).column).isEqualTo(rootNode.column);
        assertThat(rootNode.children.get(2).parent).isEqualTo(rootNode);

        assertThat(rootNode.children.get(3).row).isEqualTo(rootNode.row);
        assertThat(rootNode.children.get(3).column).isEqualTo(rootNode.column - 1);
        assertThat(rootNode.children.get(3).parent).isEqualTo(rootNode);
    }
}
