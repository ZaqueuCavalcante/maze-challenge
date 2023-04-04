package stone;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;

import org.junit.Test;

public class TreeGetFilteredNodesTests {
    @Test
    public void should_filter_the_root_node() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(1);
        assertThat(nodes.size()).isEqualTo(1);
        assertThat(nodes.contains(new Node(0, 0, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_01_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(1, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(2);
        assertThat(nodes.size()).isEqualTo(2);
        assertThat(nodes.contains(new Node(0, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 0, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_02_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(2, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(4);
        assertThat(nodes.size()).isEqualTo(3);
        assertThat(nodes.contains(new Node(2, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(0, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 1, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_03_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(3, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(5);
        assertThat(nodes.size()).isEqualTo(4);
        assertThat(nodes.contains(new Node(0, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 2, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_04_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(4, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(5);
        assertThat(nodes.size()).isEqualTo(5);
        assertThat(nodes.contains(new Node(0, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(4, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 1, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_05_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(5, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(4);
        assertThat(nodes.size()).isEqualTo(3);
        assertThat(nodes.contains(new Node(1, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(5, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 2, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_06_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(6, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(10);
        assertThat(nodes.size()).isEqualTo(7);
        assertThat(nodes.contains(new Node(0, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(6, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(4, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(5, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 3, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_07_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(7, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(8);
        assertThat(nodes.size()).isEqualTo(6);
        assertThat(nodes.contains(new Node(2, 3, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(4, 3, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(5, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(5, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 4, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_08_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(8, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(4);
        assertThat(nodes.size()).isEqualTo(4);
        assertThat(nodes.contains(new Node(0, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(6, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(5, 1, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_09_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(9, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(5);
        assertThat(nodes.size()).isEqualTo(5);
        assertThat(nodes.contains(new Node(0, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(4, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(5, 0, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_10_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(10, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(7);
        assertThat(nodes.size()).isEqualTo(6);
        assertThat(nodes.contains(new Node(0, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(4, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(6, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 1, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_11_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(11, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(3);
        assertThat(nodes.size()).isEqualTo(3);
        assertThat(nodes.contains(new Node(0, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(4, 1, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_12_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(12, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(7);
        assertThat(nodes.size()).isEqualTo(6);
        assertThat(nodes.contains(new Node(0, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(4, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(4, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 1, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_13_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(13, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(7);
        assertThat(nodes.size()).isEqualTo(7);
        assertThat(nodes.contains(new Node(0, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 1, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 3, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(3, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(5, 2, null, tree.ids, 1))).isTrue();
    }

    @Test
    public void should_filter_the_level_14_nodes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        tree.goToLevel(14, maze);

        // Act
        HashSet<Node> nodes = tree.getFilteredNodes();

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(6);
        assertThat(nodes.size()).isEqualTo(5);
        assertThat(nodes.contains(new Node(2, 0, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(0, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(6, 2, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(2, 4, null, tree.ids, 1))).isTrue();
        assertThat(nodes.contains(new Node(1, 1, null, tree.ids, 1))).isTrue();
    }
}
