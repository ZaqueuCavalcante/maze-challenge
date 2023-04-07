package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import stone.code.mazes.Maze;
import stone.code.mazes.Maze00Micro;

public class MazeGetCellCoordinatesTests {
    @Test
    public void should_get_the_cell_00_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(0);
        int column = maze.getCellColumn(0);

        // Assert
        assertThat(row).isEqualTo(0);
        assertThat(column).isEqualTo(0);
    }

    @Test
    public void should_get_the_cell_01_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(1);
        int column = maze.getCellColumn(1);

        // Assert
        assertThat(row).isEqualTo(0);
        assertThat(column).isEqualTo(1);
    }

    @Test
    public void should_get_the_cell_02_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(2);
        int column = maze.getCellColumn(2);

        // Assert
        assertThat(row).isEqualTo(0);
        assertThat(column).isEqualTo(2);
    }

    @Test
    public void should_get_the_cell_03_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(3);
        int column = maze.getCellColumn(3);

        // Assert
        assertThat(row).isEqualTo(0);
        assertThat(column).isEqualTo(3);
    }

    @Test
    public void should_get_the_cell_04_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(4);
        int column = maze.getCellColumn(4);

        // Assert
        assertThat(row).isEqualTo(1);
        assertThat(column).isEqualTo(0);
    }

    @Test
    public void should_get_the_cell_05_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(5);
        int column = maze.getCellColumn(5);

        // Assert
        assertThat(row).isEqualTo(1);
        assertThat(column).isEqualTo(1);
    }

    @Test
    public void should_get_the_cell_06_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(6);
        int column = maze.getCellColumn(6);

        // Assert
        assertThat(row).isEqualTo(1);
        assertThat(column).isEqualTo(2);
    }

    @Test
    public void should_get_the_cell_07_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(7);
        int column = maze.getCellColumn(7);

        // Assert
        assertThat(row).isEqualTo(1);
        assertThat(column).isEqualTo(3);
    }

    @Test
    public void should_get_the_cell_08_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(8);
        int column = maze.getCellColumn(8);

        // Assert
        assertThat(row).isEqualTo(2);
        assertThat(column).isEqualTo(0);
    }

    @Test
    public void should_get_the_cell_09_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(9);
        int column = maze.getCellColumn(9);

        // Assert
        assertThat(row).isEqualTo(2);
        assertThat(column).isEqualTo(1);
    }

    @Test
    public void should_get_the_cell_10_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(10);
        int column = maze.getCellColumn(10);

        // Assert
        assertThat(row).isEqualTo(2);
        assertThat(column).isEqualTo(2);
    }

    @Test
    public void should_get_the_cell_11_coordinates() {
        // Arrange
        Maze maze = new Maze00Micro();

        // Act
        int row = maze.getCellRow(11);
        int column = maze.getCellColumn(11);

        // Assert
        assertThat(row).isEqualTo(2);
        assertThat(column).isEqualTo(3);
    }
}
