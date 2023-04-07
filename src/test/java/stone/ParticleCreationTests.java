package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import stone.code.Particle;
import stone.code.mazes.Maze;
import stone.code.mazes.Maze01Ton;

public class ParticleCreationTests {
    @Test
    public void should_create_a_new_particle_with_correct_values() {
        // Arrange
        int turn = 0;
        Maze maze = new Maze01Ton();

        // Act
        Particle particle = new Particle(turn, maze);

        // Assert
        assertThat(particle.turn).isEqualTo(turn);
        assertThat(particle.row).isEqualTo(0);
        assertThat(particle.column).isEqualTo(0);
    }
}
