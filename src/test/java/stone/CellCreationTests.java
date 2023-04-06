package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import stone.code.Cell;

public class CellCreationTests {
    @Test
    public void should_create_a_new_cell_with_correct_values() {
        // Arrange
        int row = 1;
        int column = 2;
        int value = 3;

        // Act
        Cell cell = new Cell(row, column, value);

        // Assert
        assertThat(cell.row).isEqualTo(row);
        assertThat(cell.column).isEqualTo(column);
        assertThat(cell.value).isEqualTo(value);
    }
}
