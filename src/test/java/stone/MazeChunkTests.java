package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class MazeChunkTests {
    @Test
    public void should_create_a_new_maze_chunk_with_correct_rows_and_columns() {
        // Arrange / Act
        MazeChunk chunk = new MazeChunk();

        // Assert
        assertThat(chunk.rows).isEqualTo(10);
        assertThat(chunk.columns).isEqualTo(10);
    }

    @Test
    public void should_create_a_new_maze_chunk_with_correct_values() {
        // Arrange / Act
        MazeChunk chunk = new MazeChunk();

        // Assert
        assertThat(chunk.values[0]).isEqualTo(new int[] { 9, 9, 9, 9, 9, 9, 1, 9, 9, 9 });
        assertThat(chunk.values[1]).isEqualTo(new int[] { 9, 0, 9, 9, 9, 0, 1, 9, 9, 1 });
        assertThat(chunk.values[2]).isEqualTo(new int[] { 9, 9, 9, 1, 9, 9, 9, 9, 0, 9 });
        assertThat(chunk.values[3]).isEqualTo(new int[] { 9, 1, 1, 9, 9, 1, 9, 1, 9, 9 });
        assertThat(chunk.values[4]).isEqualTo(new int[] { 1, 9, 9, 9, 9, 0, 9, 9, 1, 9 });
        assertThat(chunk.values[5]).isEqualTo(new int[] { 9, 9, 9, 1, 9, 9, 9, 1, 0, 9 });
        assertThat(chunk.values[6]).isEqualTo(new int[] { 9, 1, 9, 9, 9, 9, 1, 1, 9, 9 });
        assertThat(chunk.values[7]).isEqualTo(new int[] { 1, 9, 9, 9, 9, 9, 9, 9, 1, 9 });
        assertThat(chunk.values[8]).isEqualTo(new int[] { 9, 9, 1, 9, 9, 1, 9, 1, 1, 9 });
        assertThat(chunk.values[9]).isEqualTo(new int[] { 9, 1, 9, 9, 1, 9, 9, 9, 9, 9 });
    }

    @Test
    public void should_create_a_new_maze_chunk_with_correct_initial_state() {
        // Arrange / Act
        MazeChunk chunk = new MazeChunk();

        // Assert
        assertThat(chunk.initial[0]).isEqualTo(new int[] { 9, 9, 9, 9, 9, 9, 1, 9, 9, 9 });
        assertThat(chunk.initial[1]).isEqualTo(new int[] { 9, 0, 9, 9, 9, 0, 1, 9, 9, 1 });
        assertThat(chunk.initial[2]).isEqualTo(new int[] { 9, 9, 9, 1, 9, 9, 9, 9, 0, 9 });
        assertThat(chunk.initial[3]).isEqualTo(new int[] { 9, 1, 1, 9, 9, 1, 9, 1, 9, 9 });
        assertThat(chunk.initial[4]).isEqualTo(new int[] { 1, 9, 9, 9, 9, 0, 9, 9, 1, 9 });
        assertThat(chunk.initial[5]).isEqualTo(new int[] { 9, 9, 9, 1, 9, 9, 9, 1, 0, 9 });
        assertThat(chunk.initial[6]).isEqualTo(new int[] { 9, 1, 9, 9, 9, 9, 1, 1, 9, 9 });
        assertThat(chunk.initial[7]).isEqualTo(new int[] { 1, 9, 9, 9, 9, 9, 9, 9, 1, 9 });
        assertThat(chunk.initial[8]).isEqualTo(new int[] { 9, 9, 1, 9, 9, 1, 9, 1, 1, 9 });
        assertThat(chunk.initial[9]).isEqualTo(new int[] { 9, 1, 9, 9, 1, 9, 9, 9, 9, 9 });
    }

    @Test
    public void the_solved_maze_chunk_should_have_only_0s_and_1s() {
        // Arrange
        MazeChunk chunk = new MazeChunk();

        // Act
        chunk.solve();

        // Assert
        for (int row = 0; row < chunk.rows; row++) {
            for (int column = 0; column < chunk.columns; column++) {
                int value = chunk.values[row][column];
                assertThat(value == 0 || value == 1).isTrue();
            }
        }
    }

    @Test
    public void the_solved_maze_chunk_should_change_only_the_9s_values() {
        // Arrange
        MazeChunk chunk = new MazeChunk();

        // Act
        chunk.solve();

        // Assert
        for (int row = 0; row < chunk.rows; row++) {
            for (int column = 0; column < chunk.columns; column++) {
                int value = chunk.values[row][column];

                if (value == 0) {
                    assertThat(chunk.initial[row][column]).isEqualTo(0);
                }

                if (value == 1) {
                    assertThat(chunk.initial[row][column]).isEqualTo(1);
                }
            }
        }
    }

    @Test
    public void the_solved_maze_chunk_should_not_have_2x2_0s() {
        // Arrange
        MazeChunk chunk = new MazeChunk();

        // Act
        chunk.solve();

        // Assert
        for (int row = 0; row < chunk.rows - 1; row++) {
            for (int column = 0; column < chunk.columns - 1; column++) {
                int value00 = chunk.values[row][column];
                int value01 = chunk.values[row][column + 1];
                int value02 = chunk.values[row + 1][column];
                int value03 = chunk.values[row + 1][column + 1];

                assertThat(value00 == 0 && value00 == value01 && value00 == value02 && value00 == value03).isFalse();
            }
        }
    }

    @Test
    public void the_solved_maze_chunk_should_not_have_2x2_1s() {
        // Arrange
        MazeChunk chunk = new MazeChunk();

        // Act
        chunk.solve();

        // Assert
        for (int row = 0; row < chunk.rows - 1; row++) {
            for (int column = 0; column < chunk.columns - 1; column++) {
                int value00 = chunk.values[row][column];
                int value01 = chunk.values[row][column + 1];
                int value02 = chunk.values[row + 1][column];
                int value03 = chunk.values[row + 1][column + 1];

                assertThat(value00 == 1 && value00 == value01 && value00 == value02 && value00 == value03).isFalse();
            }
        }
    }

    @Test
    public void the_solved_maze_chunk_should_have_all_0s_connected() {
        // Arrange
        MazeChunk chunk = new MazeChunk();

        int zeros = 0;
        int ones = 0;
        for (int row = 0; row < chunk.rows; row++) {
            for (int column = 0; column < chunk.columns; column++) {
                int value = chunk.values[row][column];
                if (value == 0) {
                    zeros++;
                } else if (value == 1) {
                    ones++;
                }
            }
        }

        // Act
        chunk.solve();

        // Assert
        assertThat(zeros + ones).isEqualTo(chunk.rows + chunk.columns);

        for (int row = 0; row < chunk.rows - 1; row++) {
            for (int column = 0; column < chunk.columns - 1; column++) {
                int value00 = chunk.values[row][column];
                int value01 = chunk.values[row][column + 1];
                int value02 = chunk.values[row + 1][column];
                int value03 = chunk.values[row + 1][column + 1];

                assertThat(value00 == 1 && value00 == value01 && value00 == value02 && value00 == value03).isFalse();
            }
        }
    }
}
