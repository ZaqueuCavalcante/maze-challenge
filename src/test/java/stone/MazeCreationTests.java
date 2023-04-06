package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import stone.code.CellType;
import stone.code.Maze;
import stone.code.Maze01Ton;

public class MazeCreationTests {
    @Test
    public void should_create_a_new_maze_01_with_correct_rows_and_columns() {
        // Arrange / Act
        Maze maze = new Maze01Ton();

        // Assert
        assertThat(maze.rows).isEqualTo(7);
        assertThat(maze.columns).isEqualTo(8);
    }

    @Test
    public void should_create_a_new_maze_01_with_correct_start_cell() {
        // Arrange / Act
        Maze maze = new Maze01Ton();

        // Assert
        assertThat(maze.startCell.row).isEqualTo(0);
        assertThat(maze.startCell.column).isEqualTo(0);
        assertThat(maze.startCell.value).isEqualTo(CellType.START);
    }

    @Test
    public void should_create_a_new_maze_01_with_correct_end_cell() {
        // Arrange / Act
        Maze maze = new Maze01Ton();

        // Assert
        assertThat(maze.endCell.row).isEqualTo(6);
        assertThat(maze.endCell.column).isEqualTo(7);
        assertThat(maze.endCell.value).isEqualTo(CellType.END);
    }

    @Test
    public void should_create_a_new_maze_01_with_correct_current_initial_state() {
        // Arrange / Act
        Maze maze = new Maze01Ton();

        // Assert
        assertThat(maze.current[0]).isEqualTo(new int[] { 3, 0, 0, 0, 0, 0, 0, 0 });
        assertThat(maze.current[1]).isEqualTo(new int[] { 0, 0, 0, 0, 1, 0, 0, 0 });
        assertThat(maze.current[2]).isEqualTo(new int[] { 0, 0, 1, 0, 1, 1, 0, 0 });
        assertThat(maze.current[3]).isEqualTo(new int[] { 0, 1, 1, 0, 0, 1, 1, 0 });
        assertThat(maze.current[4]).isEqualTo(new int[] { 0, 0, 1, 0, 1, 1, 0, 0 });
        assertThat(maze.current[5]).isEqualTo(new int[] { 0, 0, 0, 0, 1, 0, 0, 0 });
        assertThat(maze.current[6]).isEqualTo(new int[] { 0, 0, 0, 0, 0, 0, 0, 4 });
    }

    @Test
    public void should_create_a_new_maze_01_with_correct_next_initial_state() {
        // Arrange / Act
        Maze maze = new Maze01Ton();

        // Assert
        assertThat(maze.next[0]).isEqualTo(new int[] { 3, 0, 0, 0, 0, 0, 0, 0 });
        assertThat(maze.next[1]).isEqualTo(new int[] { 0, 0, 0, 1, 0, 1, 0, 0 });
        assertThat(maze.next[2]).isEqualTo(new int[] { 0, 1, 0, 0, 0, 1, 1, 0 });
        assertThat(maze.next[3]).isEqualTo(new int[] { 0, 0, 0, 0, 0, 1, 0, 0 });
        assertThat(maze.next[4]).isEqualTo(new int[] { 0, 1, 0, 0, 0, 1, 1, 0 });
        assertThat(maze.next[5]).isEqualTo(new int[] { 0, 0, 0, 1, 0, 1, 0, 0 });
        assertThat(maze.next[6]).isEqualTo(new int[] { 0, 0, 0, 0, 0, 0, 0, 4 });
    }
}
