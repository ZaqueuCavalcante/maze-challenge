package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class Maze02SolutionsTests {
    @Test
    public void should_test_one_correct_solution_for_maze_02() {
        // Arrange
        Maze maze = new Maze(MazeOption._02);

        int row = maze.startCell.row;
        int column = maze.startCell.column;
        String solution = "RRDDRDRURRRRDRRRDDRRDDDURRDRRUDRDRLDUDDDDDRRDDRRDDRDRLUDRRLDRRDDRDUDDRRRRRRDDRRRRURDDRDURDDLRDDRRDRDDUDLLDRDDURUUULRURLRDDRDDLRRDRRRDRRRUDDRRRURLLDLRRUDDRRDDDRRLRRRRRRRLRRUDDDDURRRUDUURURULRDLURDURRDRDRDDLRDDDUDRRRDRURDRLDRRULDLDDDRDLLDDDDURULDRRRRLRURUDLRDDDDLLLRDDDDUDRR";

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

            assertThat(maze.isObstacle(row, column)).isFalse();
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
}
