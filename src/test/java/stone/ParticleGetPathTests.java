package stone;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import stone.code.Particle;
import stone.code.mazes.Maze;
import stone.code.mazes.Maze01Ton;

public class ParticleGetPathTests {
    @Test
    public void should_get_the_correct_particle_path_after_in_on_turn_0_and_R_D_R_moves() {
        // Arrange
        Maze maze = new Maze01Ton();
        Particle particle = new Particle(0, maze);
        particle.right();
        particle.down();
        particle.right();

        // Act
        String path = particle.getFormatedPath();

        // Assert
        assertThat(path).isEqualTo("0 R D R");
    }

    @Test
    public void should_get_the_correct_particle_path_after_in_on_turn_42_and_R_D_R_L_U_moves() {
        // Arrange
        Maze maze = new Maze01Ton();
        Particle particle = new Particle(42, maze);
        particle.right();
        particle.down();
        particle.right();
        particle.left();
        particle.up();

        // Act
        String path = particle.getFormatedPath();

        // Assert
        assertThat(path).isEqualTo("42 R D R L U");
    }
}
