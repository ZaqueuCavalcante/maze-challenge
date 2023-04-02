package stone;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import processing.core.PApplet;

public class Maze03SolutionsTests {
    @Test
    public void should_test_one_correct_solution_for_maze_03() {
        // Arrange
        Maze maze = new Maze03();
        String path = "RRRRRRRRRRRRRRRRDRRRDDURURRLRDDULURDRDURRRLRDLDURUDURDDDDUDDDDRRDURRDRDDRDRDDURRDRDDRRRDRRRRURLUULLDDLRRRRRRRDRLRDDDRDRULRURRRRDURLLLLRRRDDDDDRRRRRRRRRRRDUUUURRRRLDRDDDDRRLRDDRRDLRRRRRDRRRRLRRDRDDRLDDRDDURRURRRUURRRDRDUURDRDDRDDDRRURUDRRDURDRRRRRRDDRRDDDRRRURDURDUDDDRRDRRDLDDLDDRDDRRRULRDRRRDDDDDURDRUDLRRRRRR";

        // Act
        boolean isSolution = maze.isSolution(path);

        // Assert
        assertThat(isSolution).isTrue();
    }

    @Test
    public void should_test_one_wrong_solution_for_maze_03() {
        // Arrange
        Maze maze = new Maze03();
        String path = "RRDDRDRURRRRDRRRDDRRDDDURRDRRUDRDRLDUDDDDDRRDDRRDDRDRLUDRR";

        // Act
        boolean isSolution = maze.isSolution(path);

        // Assert
        assertThat(isSolution).isFalse();
    }

    @Test
    public void should_test_many_correct_solutions_for_maze_03() {
        File file = new File("src/test/java/stone/solutions/solutions_maze_03.txt");
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
            Maze maze = new Maze03();

            // Act
            boolean isSolution = maze.isSolution(path);

            // Assert
            assertThat(isSolution).isTrue();
        }
    }
}
