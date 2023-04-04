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
    public void should_create_a_new_maze_chunk_with_correct_initial_state() {
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
}
