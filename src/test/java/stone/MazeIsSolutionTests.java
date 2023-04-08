package stone;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import processing.core.PApplet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
        File file = new File("src/test/java/stone/solutions/solutions_maze_01_ton.txt");
        InputStream input;
        String[] paths = new String[0];

        try {
            input = new FileInputStream(file);
            paths = PApplet.loadStrings(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> pathsAL = new ArrayList<>(Arrays.asList(paths));
        Maze maze = new Maze01Ton();

        // Act
        boolean isSolution = maze.isSolution(pathsAL);

        // Assert
        assertThat(isSolution).isTrue();
    }

    @Test
    public void should_test_solution_for_maze_00_sinuca_15x15_with_7_particles() {
        // Arrange
        File file = new File("src/test/java/stone/solutions/solutions_maze_05_sinuca_15x15.txt");
        InputStream input;
        String[] paths = new String[0];

        try {
            input = new FileInputStream(file);
            paths = PApplet.loadStrings(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> pathsAL = new ArrayList<>(Arrays.asList(paths));
        Maze maze = new Maze05Sinuca15x15();

        // Act
        boolean isSolution = maze.isSolution(pathsAL);

        // Assert
        assertThat(isSolution).isTrue();
    }
}
