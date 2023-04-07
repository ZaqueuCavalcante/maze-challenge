package stone;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import stone.code.mazes.Maze;
import stone.code.mazes.Maze01Ton;
import stone.code.mazes.Maze05Sinuca15x15;

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

    @Test
    public void should_test_solution_for_maze_00_sinuca_15x15_with_7_particles() {
        // Arrange
        Maze maze = new Maze05Sinuca15x15();

        ArrayList<String> paths = new ArrayList<>();
        paths.add("0 D U D U D U D D D D U D D D U D R L D D D D U U D R R R L U D D R R R R D R D D D R R R R R L R R R");
        paths.add("1 R R L L R R L L R D D D R R L D U R D R D D R D D R D L D L R R R D R R U R R U L U L R D R D D D R R D R D");
        paths.add("15 R R R R D D R D R D R D D D U D L D U L L R U D R L D D R R L L R D L U D R R R R L R D D R L R R D R R R D");
        paths.add("20 D D U D R L U D D D D D D D D D R R R U L U D D D D R R U R R R D R R R D D R L R R R R");
        paths.add("33 R R D D D L U U U L R D U L R R D R R R U R D U L L D D R D R R R R R R D R R D L L D U L D D D L U D R D R R D D D D R R D");
        paths.add("34 D D D D R R R D R R D R L L L R R L D L D D L R D R D R R D R D D R U R R R D R R R");
        paths.add("48 D D D R L R D R D R D D R R D L D R R R D D D D D R L R R R R R U R R D");

        // Act
        boolean isSolution = maze.isSolution(paths);

        // Assert
        assertThat(isSolution).isTrue();
    }
}
