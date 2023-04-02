package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class Maze04SolutionsTests {
    @Test
    public void should_test_one_correct_solution_for_maze_04() {
        // Arrange
        Maze maze = new Maze04();
        String path = "DDDDDDDDDDDDDDDDRRRRRRRRRRRRRRRDRRRRRRRRRRRRRRRRRDRRRRRRRDRDRLDRDRRDRUDDDDDRURRDRUDDDRURDDDUDRRDDRRRUDRDLRRDURRLDRDLDRLDDDDDLLLDDUDDRDRDDRRDDLDRDDLDRDLDDRRDRDLRRDRLDDDDRDRRDRDRURDDLDRRUURURRDRRRDDDRDDLRDDDRDDDDRDDRDRURRRRDDUDDRDUDLDRLRDDDDRDDLRRRLUDDRDDDDLDRDLLRDDRDDLULDDDRRDDRUDDDDRDLRRUDRDDDDDDDDRRRDLLDRDURDDRURRRRRRDRURRDDRRRURRRRLURDRDDRRRDDDUDLLRDDDDDDLUDRDRDRDRURDRRDRDDLDDDUDDLDRRDDDRRDDDDRDDDDDRDLRRDRLDURRDRRRDDLRDDRRLDDULRDDDDDLDLULLLRRDDLDDLDRRDDRRRRRRRLRRRRRRDDRURUDDRDRRUDDDDRDLLDRRRRRRDURRRRDRRRDDDDDDUDRRURRRRDURDRRRLLDDDDRRRDDRRLLUUDDRRDULUDDRDDLDRRRURDRRRLURDRURRURDDRRRRRURRRRDRDUDURRUDRRDRRDDRRURRRRRLRDRRRUDDRDURUDRLRDDDRURDRRDDRRRDLDRRRLRRDDLDRDUDRDLRRDRDDURRRUDRDDDDDRDDDRLRRRRRRLRLRRRR";

        // Act
        boolean isSolution = maze.isSolution(path);

        // Assert
        assertThat(isSolution).isTrue();
    }

    @Test
    public void should_test_one_wrong_solution_for_maze_04() {
        // Arrange
        Maze maze = new Maze04();
        String path = "RRDDRDRURRRRDRRRDDRRDDDURRDRRUDRDRLDUDDDDDRRDDRRDDRDRLUDRR";

        // Act
        boolean isSolution = maze.isSolution(path);

        // Assert
        assertThat(isSolution).isFalse();
    }
}
