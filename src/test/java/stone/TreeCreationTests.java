package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class TreeCreationTests {
    @Test
    public void should_create_a_new_tree_with_correct_values() {
        // Arrange
        Maze maze = new Maze(MazeOption._01);

        // Act
        Tree tree = new Tree(maze);

        // Assert
        assertThat(tree.root.row).isEqualTo(0);
        assertThat(tree.root.column).isEqualTo(0);

        assertThat(tree.level).isEqualTo(0);
        assertThat(tree.levelNodes.size()).isEqualTo(1);
    }
}
