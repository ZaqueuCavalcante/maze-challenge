package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import stone.code.Maze;
import stone.code.Maze01Ton;

public class MazeGetNeighborsTests {
    @Test
    public void should_get_neighbors_for_the_maze_01_state_0() {
        // Arrange / Act
        Maze maze = new Maze01Ton();

        // Assert
        assertThat(maze.currentNeighbors[0]).isEqualTo(new int[] { 0, 0, 0, 1, 1, 1, 0, 0 });
        assertThat(maze.currentNeighbors[1]).isEqualTo(new int[] { 0, 1, 1, 3, 2, 3, 1, 0 });
        assertThat(maze.currentNeighbors[2]).isEqualTo(new int[] { 1, 3, 2, 4, 3, 4, 3, 1 });
        assertThat(maze.currentNeighbors[3]).isEqualTo(new int[] { 1, 3, 3, 5, 5, 5, 3, 1 });
        assertThat(maze.currentNeighbors[4]).isEqualTo(new int[] { 1, 3, 2, 4, 3, 4, 3, 1 });
        assertThat(maze.currentNeighbors[5]).isEqualTo(new int[] { 0, 1, 1, 3, 2, 3, 1, 0 });
        assertThat(maze.currentNeighbors[6]).isEqualTo(new int[] { 0, 0, 0, 1, 1, 1, 0, 0 });
    }

    @Test
    public void should_get_neighbors_for_the_maze_01_state_1() {
        // Arrange / Act
        Maze maze = new Maze01Ton();
        maze.shift();

        // Assert
        assertThat(maze.currentNeighbors[0]).isEqualTo(new int[] { 0, 0, 1, 1, 2, 1, 1, 0 });
        assertThat(maze.currentNeighbors[1]).isEqualTo(new int[] { 1, 1, 2, 0, 3, 2, 3, 1 });
        assertThat(maze.currentNeighbors[2]).isEqualTo(new int[] { 1, 0, 2, 1, 4, 3, 3, 1 });
        assertThat(maze.currentNeighbors[3]).isEqualTo(new int[] { 2, 2, 2, 0, 3, 4, 5, 2 });
        assertThat(maze.currentNeighbors[4]).isEqualTo(new int[] { 1, 0, 2, 1, 4, 3, 3, 1 });
        assertThat(maze.currentNeighbors[5]).isEqualTo(new int[] { 1, 1, 2, 0, 3, 2, 3, 1 });
        assertThat(maze.currentNeighbors[6]).isEqualTo(new int[] { 0, 0, 1, 1, 2, 1, 1, 0 });
    }

    @Test
    public void should_get_neighbors_for_the_maze_01_state_2() {
        // Arrange / Act
        Maze maze = new Maze01Ton();
        maze.shift();
        maze.shift();

        // Assert
        assertThat(maze.currentNeighbors[0]).isEqualTo(new int[] { 0, 1, 1, 3, 1, 3, 1, 1 });
        assertThat(maze.currentNeighbors[1]).isEqualTo(new int[] { 0, 2, 1, 4, 1, 3, 0, 1 });
        assertThat(maze.currentNeighbors[2]).isEqualTo(new int[] { 2, 5, 3, 5, 3, 4, 3, 2 });
        assertThat(maze.currentNeighbors[3]).isEqualTo(new int[] { 1, 4, 3, 4, 1, 1, 2, 0 });
        assertThat(maze.currentNeighbors[4]).isEqualTo(new int[] { 2, 5, 3, 5, 3, 4, 3, 2 });
        assertThat(maze.currentNeighbors[5]).isEqualTo(new int[] { 0, 2, 1, 4, 1, 3, 0, 1 });
        assertThat(maze.currentNeighbors[6]).isEqualTo(new int[] { 0, 1, 1, 3, 1, 3, 1, 1 });
    }
}
