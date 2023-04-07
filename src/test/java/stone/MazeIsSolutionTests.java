package stone;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import stone.code.mazes.Maze;
import stone.code.mazes.Maze01Ton;

public class MazeIsSolutionTests {
    @Test
    public void should_test_one_correct_solution_for_maze_01() {
        // Arrange
        Maze maze = new Maze01Ton();
        String path = "0 D D D D D R R L U U U R R R D R L D D R R D R";
        ArrayList<String> paths = new ArrayList<>();
        paths.add(path);

        // Act
        boolean isSolution = maze.isSolution(paths);

        // Assert
        assertThat(isSolution).isTrue();
    }

    @Test
    public void should_test_solution_for_maze_01_with_2_particles() {
        // Arrange
        Maze maze = new Maze01Ton();
        ArrayList<String> paths = new ArrayList<>();
        paths.add("0 D D D D D R R L U U U R R R D R L D D R R D R");
        paths.add("1 R R L D D D D D R R R R R D R");

        // Act
        boolean isSolution = maze.isSolution(paths);

        // Assert
        assertThat(isSolution).isTrue();
    }

    @Test
    public void should_test_other_solution_for_maze_01_with_2_particles() {
        // Arrange
        Maze maze = new Maze01Ton();
        ArrayList<String> paths = new ArrayList<>();
        paths.add("0 D D D D D R R L U U U R R R D R L D D R R R D");
        paths.add("1 R R L D D D D D R R R R R R D");

        // Act
        boolean isSolution = maze.isSolution(paths);

        // Assert
        assertThat(isSolution).isTrue();
    }

    @Test
    public void should_test_solution_for_maze_01_with_5_particles() {
        // Arrange
        Maze maze = new Maze01Ton();

        ArrayList<String> paths = new ArrayList<>();
        paths.add("0 D D D D D R R L U U U R R R D R L D D R R D R");
        paths.add("1 R R L D D D D D R R R R R D R");
        paths.add("5 R R D R D R D R D R U D D D R");
        paths.add("9 D D D D D R R R R L L R L R L R R R R R D");
        paths.add("10 R D U R R R D R R U D U D U R L R D D D D D D");

        // Act
        boolean isSolution = maze.isSolution(paths);

        // Assert
        assertThat(isSolution).isTrue();
    }
}
