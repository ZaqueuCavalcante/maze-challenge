package stone;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import processing.core.PApplet;

public class MazeChallenge02SolutionsTests {
    @Test
    public void should_test_many_correct_solutions_for_maze_challenge_02_without_use_lifes() {
        File file = new File("src/test/java/stone/solutions/solutions_maze_challenge_02_without_use_lifes.txt");
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
            Maze maze = new MazeChallenge02();

            // Act
            boolean isSolution = maze.isSolution(path, 1);

            // Assert
            assertThat(isSolution).isTrue();
        }
    }

    @Test
    public void should_test_many_correct_solutions_for_maze_challenge_02_using_6_lifes() {
        File file = new File("src/test/java/stone/solutions/solutions_maze_challenge_02_using_lifes.txt");
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
            Maze maze = new MazeChallenge02();

            // Act
            boolean isSolution = maze.isSolution(path, 6);

            // Assert
            assertThat(isSolution).isTrue();
        }
    }
}
