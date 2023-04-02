package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class MazeShiftTests {
    @Test
    public void should_shift_the_maze_01_to_the_state_3() {
        // Arrange
        Maze maze = new Maze01();

        // Act
        maze.shift();
        maze.shift();

        // Assert
        assertThat(maze.current[0]).isEqualTo(new int[] { 3, 0, 0, 0, 1, 0, 0, 0 });
        assertThat(maze.current[1]).isEqualTo(new int[] { 0, 0, 1, 0, 1, 0, 1, 0 });
        assertThat(maze.current[2]).isEqualTo(new int[] { 0, 0, 1, 0, 0, 0, 0, 0 });
        assertThat(maze.current[3]).isEqualTo(new int[] { 1, 1, 1, 0, 1, 1, 0, 1 });
        assertThat(maze.current[4]).isEqualTo(new int[] { 0, 0, 1, 0, 0, 0, 0, 0 });
        assertThat(maze.current[5]).isEqualTo(new int[] { 0, 0, 1, 0, 1, 0, 1, 0 });
        assertThat(maze.current[6]).isEqualTo(new int[] { 0, 0, 0, 0, 1, 0, 0, 4 });
    }

    @Test
    public void should_shift_the_maze_01_to_the_state_4() {
        // Arrange
        Maze maze = new Maze01();

        // Act
        maze.shift();
        maze.shift();
        maze.shift();

        // Assert
        assertThat(maze.current[0]).isEqualTo(new int[] { 3, 0, 0, 1, 0, 1, 0, 0 });
        assertThat(maze.current[1]).isEqualTo(new int[] { 0, 1, 0, 0, 0, 1, 0, 0 });
        assertThat(maze.current[2]).isEqualTo(new int[] { 1, 0, 0, 0, 1, 0, 1, 1 });
        assertThat(maze.current[3]).isEqualTo(new int[] { 0, 1, 0, 0, 0, 0, 1, 0 });
        assertThat(maze.current[4]).isEqualTo(new int[] { 1, 0, 0, 0, 1, 0, 1, 1 });
        assertThat(maze.current[5]).isEqualTo(new int[] { 0, 1, 0, 0, 0, 1, 0, 0 });
        assertThat(maze.current[6]).isEqualTo(new int[] { 0, 0, 0, 1, 0, 1, 0, 4 });
    }

    @Test
    public void should_shift_the_maze_01_to_the_state_5() {
        // Arrange
        Maze maze = new Maze01();

        // Act
        maze.shift();
        maze.shift();
        maze.shift();
        maze.shift();

        // Assert
        assertThat(maze.current[0]).isEqualTo(new int[] { 3, 0, 1, 0, 1, 0, 1, 0 });
        assertThat(maze.current[1]).isEqualTo(new int[] { 1, 0, 1, 1, 0, 0, 0, 1 });
        assertThat(maze.current[2]).isEqualTo(new int[] { 0, 1, 1, 0, 0, 0, 0, 0 });
        assertThat(maze.current[3]).isEqualTo(new int[] { 1, 0, 0, 1, 1, 0, 1, 0 });
        assertThat(maze.current[4]).isEqualTo(new int[] { 0, 1, 1, 0, 0, 0, 0, 0 });
        assertThat(maze.current[5]).isEqualTo(new int[] { 1, 0, 1, 1, 0, 0, 0, 1 });
        assertThat(maze.current[6]).isEqualTo(new int[] { 0, 0, 1, 0, 1, 0, 1, 4 });
    }

    @Test
    public void should_shift_the_maze_01_to_the_state_6() {
        // Arrange
        Maze maze = new Maze01();

        // Act
        maze.shift();
        maze.shift();
        maze.shift();
        maze.shift();
        maze.shift();

        // Assert
        assertThat(maze.current[0]).isEqualTo(new int[] { 3, 1, 0, 0, 0, 1, 0, 1 });
        assertThat(maze.current[1]).isEqualTo(new int[] { 0, 0, 1, 1, 1, 1, 1, 0 });
        assertThat(maze.current[2]).isEqualTo(new int[] { 1, 1, 1, 0, 1, 1, 1, 1 });
        assertThat(maze.current[3]).isEqualTo(new int[] { 0, 0, 0, 0, 0, 1, 0, 0 });
        assertThat(maze.current[4]).isEqualTo(new int[] { 1, 1, 1, 0, 1, 1, 1, 1 });
        assertThat(maze.current[5]).isEqualTo(new int[] { 0, 0, 1, 1, 1, 1, 1, 0 });
        assertThat(maze.current[6]).isEqualTo(new int[] { 0, 1, 0, 0, 0, 1, 0, 4 });
    }
}
