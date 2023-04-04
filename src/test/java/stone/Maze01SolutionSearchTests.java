package stone;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

public class Maze01SolutionSearchTests {
    @Test
    public void should_test_solutions_search_for_maze_01_with_1_life() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 1);

        // Act
        while (tree.solutions.size() == 0) {
            tree.goToNextLevel(maze);
            maze.shift();
        }

        ArrayList<String> paths = tree.getSolutionsPaths();

        for (String path : paths) {
            maze = new Maze01();
            boolean isSolution = maze.isSolution(path, 1);

            // Assert
            assertThat(isSolution).isTrue();
        }
    }

    @Test
    public void should_test_solutions_search_for_maze_01_with_6_lifes() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, 6);

        // Act
        while (tree.solutions.size() == 0) {
            tree.goToNextLevel(maze);
            maze.shift();
        }

        ArrayList<String> paths = tree.getSolutionsPaths();

        int i = 0;
        for (Node node : tree.solutions) {
            String path = paths.get(i);

            maze = new Maze01();
            boolean isSolution = maze.isSolution(path, 6);

            // Assert
            assertThat(isSolution).isTrue();
            assertThat(path.length()).isEqualTo(maze.rows + maze.columns - 2);
            assertThat(path.chars().filter(c -> c == 'R').count()).isEqualTo(maze.columns - 1);
            assertThat(path.chars().filter(c -> c == 'D').count()).isEqualTo(maze.rows - 1);
            assertThat(node.lifes).isEqualTo(4);
        }
    }

    @Test
    public void should_test_solutions_search_for_maze_01_with_infinity_life() {
        // Arrange
        Maze maze = new Maze01();
        Tree tree = new Tree(maze, Integer.MAX_VALUE);

        // Act
        while (tree.solutions.size() == 0) {
            tree.goToNextLevel(maze);
            maze.shift();
        }

        ArrayList<String> paths = tree.getSolutionsPaths();

        for (String path : paths) {
            maze = new Maze01();
            boolean isSolution = maze.isSolution(path, Integer.MAX_VALUE);

            // Assert
            assertThat(isSolution).isTrue();
            assertThat(path.length()).isEqualTo(maze.rows + maze.columns - 2);
            assertThat(path.chars().filter(c -> c == 'R').count()).isEqualTo(maze.columns - 1);
            assertThat(path.chars().filter(c -> c == 'D').count()).isEqualTo(maze.rows - 1);
        }
    }
}
