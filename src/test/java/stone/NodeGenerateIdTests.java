package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class NodeGenerateIdTests {
    @Test
    public void should_return_empty_path_for_root_node() {
        int rows = 10;
        int columns = 10;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                long id = 5;

                System.out.println(row + " " + column + " " + id);
            }
        }
    }
}
