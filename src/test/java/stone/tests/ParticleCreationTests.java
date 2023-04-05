package stone.tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import stone.code.Particle;

public class ParticleCreationTests {
    @Test
    public void should_create_a_new_particle_with_correct_values() {
        // Arrange
        int id = 1;
        int turn = 0;

        // Act
        Particle particle = new Particle(id, turn);

        // Assert
        assertThat(particle.id).isEqualTo(id);
        assertThat(particle.turn).isEqualTo(turn);
        assertThat(particle.row).isEqualTo(0);
        assertThat(particle.column).isEqualTo(0);
    }
}
