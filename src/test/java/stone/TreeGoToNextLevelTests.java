package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class TreeGoToNextLevelTests {
    @Test
    public void should_start_at_root_node() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act / Assert
        assertThat(tree.levelNodes.size()).isEqualTo(1);
        assertThat(tree.levelNodes.getOrDefault(tree.ids[0][0], null)).isNotNull();
    }

    @Test
    public void should_expand_to_the_level_01() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        tree.goToLevel(1, maze);

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(2);
        assertThat(tree.levelNodes.getOrDefault(tree.ids[0][1], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][0], null)).isNotNull();
    }

    @Test
    public void should_expand_to_the_level_02() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        tree.goToLevel(2, maze);

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(4);
        assertThat(tree.levelNodes.getOrDefault(tree.ids[0][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[2][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[0][2], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][1], null)).isNotNull();
    }

    @Test
    public void should_expand_to_the_level_03() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        tree.goToLevel(3, maze);

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(5);
        assertThat(tree.levelNodes.getOrDefault(tree.ids[2][1], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[0][1], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[3][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][2], null)).isNotNull();
    }

    @Test
    public void should_expand_to_the_level_04() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        tree.goToLevel(4, maze);

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(5);
        assertThat(tree.levelNodes.getOrDefault(tree.ids[0][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[2][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[4][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][1], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[3][1], null)).isNotNull();
    }

    @Test
    public void should_expand_to_the_level_05() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        tree.goToLevel(5, maze);

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(4);
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[3][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[5][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[3][2], null)).isNotNull();
    }

    @Test
    public void should_expand_to_the_level_06() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        tree.goToLevel(6, maze);

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(10);
        assertThat(tree.levelNodes.getOrDefault(tree.ids[0][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[2][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[4][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[6][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[2][2], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[4][2], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][1], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[5][1], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[3][1], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[3][3], null)).isNotNull();
    }

    @Test
    public void should_expand_to_the_level_07() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        tree.goToLevel(7, maze);

        // Assert
        assertThat(tree.levelNodes.size()).isEqualTo(8);
        assertThat(tree.levelNodes.getOrDefault(tree.ids[2][3], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[4][3], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[5][0], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[1][2], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[3][2], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[5][2], null)).isNotNull();
        assertThat(tree.levelNodes.getOrDefault(tree.ids[3][4], null)).isNotNull();
    }
}
