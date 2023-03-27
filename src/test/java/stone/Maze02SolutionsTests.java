package stone;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import processing.core.PApplet;

public class Maze02SolutionsTests {
    @Test
    public void should_test_one_correct_solution_for_maze_02() {
        // Arrange
        Maze maze = new Maze(MazeOption._02);

        int row = maze.startCell.row;
        int column = maze.startCell.column;
        String solution = "DDDRRRRRDDRRRRRRURDDRLDRRDRRUDRRDRLDUDDDDDRRDDRRDRDDRDDRDDRUDRRULDDUDRRRRRRDRRDRURRDDDRUDDRLRDDRRDRDDUDDLLRRRUDLUULRURLRDDRDURULDDLDDDDRRRDRUDDRDDLRDLUDLRDRRURRRLRRLDDDRRLURDLDDDLDRRDLRRULDRRRUDDDRLRRDRURDDUDDRDRDDUDRDDRURURRRRRRUDDRLDUUUUULRURRRDRDRLDRRRDDRRRULLRDDDDUDRR";

        // Act
        for (char c : solution.toCharArray()) {
            maze.shift();

            String direction = String.valueOf(c).toString();

            if (direction.equals("U")) {
                row--;
            }
            if (direction.equals("R")) {
                column++;
            }
            if (direction.equals("D")) {
                row++;
            }
            if (direction.equals("L")) {
                column--;
            }

            assertThat(maze.currentIsObstacle(row, column)).isFalse();
        }

        // Assert
        assertThat(row).isEqualTo(maze.endCell.row);
        assertThat(column).isEqualTo(maze.endCell.column);
    }

    @Test
    public void should_test_one_of_best_solutions_for_maze_02() {
        // Arrange
        Maze maze = new Maze(MazeOption._02);

        int row = maze.startCell.row;
        int column = maze.startCell.column;
        String solution = "RDDRRDRDDRRRDURDRRRDRRDRRUDRUDRRDRDRDDDRDRDLDLRRDRDDRDDRDDRURRDLRLUDRDRRRRRDDRRRRUDRDDRRUDDLRDDRRDRDLDRLDLDRDURUUULRURLRDDRDDDLDDRRRDURDDDUUUDDRDDLRULDRUDRRRUDLRDRRLDDDRRULRDDDLDLRDRDLRRULDRRRUDDRLDRRDDDRDLDRDRRRDUDRURRLRLRRRRRRRUDULDDRUURURLURURDRRLDDRRRDDRRRULLRDDDDUDRR";

        // Act
        for (char c : solution.toCharArray()) {
            maze.shift();

            String direction = String.valueOf(c).toString();

            if (direction.equals("U")) {
                row--;
            }
            if (direction.equals("R")) {
                column++;
            }
            if (direction.equals("D")) {
                row++;
            }
            if (direction.equals("L")) {
                column--;
            }

            assertThat(maze.currentIsObstacle(row, column)).isFalse();
        }

        // Assert
        assertThat(row).isEqualTo(maze.endCell.row);
        assertThat(column).isEqualTo(maze.endCell.column);
    }

    @Test
    public void should_test_one_wrong_solution_for_maze_02() {
        // Arrange
        Maze maze = new Maze(MazeOption._02);

        int row = maze.startCell.row;
        int column = maze.startCell.column;
        String solution = "RRDDRDRURRRRDRRRDDRRDDDURRDRRUDRDRLDUDDDDDRRDDRRDDRDRLUDRR";

        // Act
        for (char c : solution.toCharArray()) {
            maze.shift();

            String direction = String.valueOf(c).toString();

            if (direction.equals("U")) {
                row--;
            }
            if (direction.equals("R")) {
                column++;
            }
            if (direction.equals("D")) {
                row++;
            }
            if (direction.equals("L")) {
                column--;
            }
        }

        // Assert
        assertThat(row).isNotEqualTo(maze.endCell.row);
        assertThat(column).isNotEqualTo(maze.endCell.column);
    }

    @Test
    public void should_test_many_correct_solutions_for_maze_02() {

        File file = new File("src/test/java/stone/solutions/solutions_maze_02.txt");
        InputStream input;
        String[] solutions = new String[0];

        try {
            input = new FileInputStream(file);
            solutions = PApplet.loadStrings(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String solution : solutions) {
            // Arrange
            Maze maze = new Maze(MazeOption._02);

            int row = maze.startCell.row;
            int column = maze.startCell.column;

            // Act
            for (char c : solution.toCharArray()) {
                maze.shift();

                String direction = String.valueOf(c).toString();

                if (direction.equals("U")) {
                    row--;
                }
                if (direction.equals("R")) {
                    column++;
                }
                if (direction.equals("D")) {
                    row++;
                }
                if (direction.equals("L")) {
                    column--;
                }

                assertThat(maze.currentIsObstacle(row, column)).isFalse();
            }

            // Assert
            assertThat(row).isEqualTo(maze.endCell.row);
            assertThat(column).isEqualTo(maze.endCell.column);
        }
    }
}
