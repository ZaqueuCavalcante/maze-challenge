package stone;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import stone.code.MovesChooser;
import stone.code.Particle;

public class MovesChooserTests {
    @Test
    public void should_return_the_unique_solution_possible() {
        // Arrange
        int[] options = new int[4];
        options[0] = -1;
        options[1] = 0;
        options[2] = 1;
        options[3] = -1;
        ArrayList<Integer> moveOptions = new ArrayList<>();
        moveOptions.add(1);

        Particle particle = new Particle(1, 0);
        particle.updateMoveOptions(options, moveOptions);

        HashMap<Integer, Particle> particles = new HashMap<>();
        particles.put(particle.id, particle);

        MovesChooser chooser = new MovesChooser(particles);

        // Act
        HashMap<Integer, Integer> moves = chooser.getMoves();

        // Assert
        assertThat(moves.size()).isEqualTo(1);
        assertThat(moves.get(1)).isEqualTo(1);
    }
}
