package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class TreeCreationTests {
    @Test
    public void should_create_a_new_tree_with_correct_values() {
        // Arrange
        Maze maze = new Maze01();

        // Act
        Tree tree = new Tree(maze);

        // Assert
        assertThat(tree.root.row).isEqualTo(0);
        assertThat(tree.root.column).isEqualTo(0);

        assertThat(tree.level).isEqualTo(0);
        assertThat(tree.levelNodes.size()).isEqualTo(1);
    }

    @Test
    public void should_create_a_new_tree_with_correct_nodes_ids_values() {
        // Arrange
        Maze maze = new Maze01();

        // Act
        Tree tree = new Tree(maze);

        // Assert
        assertThat(tree.ids[0]).isEqualTo(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
        assertThat(tree.ids[1]).isEqualTo(new int[] { 8, 9, 10, 11, 12, 13, 14, 15 });
        assertThat(tree.ids[2]).isEqualTo(new int[] { 16, 17, 18, 19, 20, 21, 22, 23 });
        assertThat(tree.ids[3]).isEqualTo(new int[] { 24, 25, 26, 27, 28, 29, 30, 31 });
        assertThat(tree.ids[4]).isEqualTo(new int[] { 32, 33, 34, 35, 36, 37, 38, 39 });
        assertThat(tree.ids[5]).isEqualTo(new int[] { 40, 41, 42, 43, 44, 45, 46, 47 });
        assertThat(tree.ids[6]).isEqualTo(new int[] { 48, 49, 50, 51, 52, 53, 54, 55 });
    }
}
