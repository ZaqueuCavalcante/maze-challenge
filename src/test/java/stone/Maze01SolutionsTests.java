package stone;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import processing.core.PApplet;

public class Maze01SolutionsTests {
    @Test
    public void should_test_one_correct_solution_for_maze_01() {
        // Arrange
        Maze maze = new Maze01();
        String path = "RRLLDRRULDDRRRLRDRDDRDR";

        // Act
        boolean isSolution = maze.isSolution(path, 1);

        // Assert
        assertThat(isSolution).isTrue();
    }

    @Test
    public void should_test_one_wrong_solution_for_maze_01() {
        // Arrange
        Maze maze = new Maze01();
        String path = "RRDLLDDRDUURRRDUDRDDRRL";

        // Act
        boolean isSolution = maze.isSolution(path, 1);

        // Assert
        assertThat(isSolution).isFalse();
    }

    @Test
    public void should_test_many_correct_solutions_for_maze_01() {
        File file = new File("src/test/java/stone/solutions/solutions_maze_01.txt");
        InputStream input;
        String[] paths = new String[0];

        try {
            input = new FileInputStream(file);
            paths = PApplet.loadStrings(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String path : paths) {
            // Arrange
            Maze maze = new Maze01();

            // Act
            boolean isSolution = maze.isSolution(path, 1);

            // Assert
            assertThat(isSolution).isTrue();
        }
    }
}
