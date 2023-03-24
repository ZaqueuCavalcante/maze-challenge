package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class TreeCreationTests {
    @Test
    public void should_create_a_new_tree_with_correct_values() {
        // Arrange
        Cell startCell = new Cell(0, 0, 3);
        Cell endCell = new Cell(6, 7, 4);

        // Act
        Tree tree = new Tree(startCell, endCell);

        // Assert
        assertThat(tree.root.row).isEqualTo(startCell.row);
        assertThat(tree.root.column).isEqualTo(startCell.column);
        assertThat(tree.target.row).isEqualTo(endCell.row);
        assertThat(tree.target.column).isEqualTo(endCell.column);

        assertThat(tree.level).isEqualTo(0);
        assertThat(tree.levelNodes.size()).isEqualTo(1);
    }
}
