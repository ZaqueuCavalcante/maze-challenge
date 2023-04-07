package stone;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.Test;

import stone.code.MovesChooser;
import stone.code.Particle;

public class MovesChooserTests {
    @Test
    public void should_return_the_unique_solution_possible() {
        // Arrange
        int[] options = new int[] { -1, 0, 1, -1 };
        int[] moveOptions = new int[] { -1, 1, -1, -1 };

        Particle particle = new Particle(1, 0);
        particle.updateMoveOptions(options, moveOptions);

        HashMap<Integer, Particle> particles = new HashMap<>();
        particles.put(particle.id, particle);

        MovesChooser chooser = new MovesChooser(particles, 11);

        // Act
        HashMap<Integer, Integer> moves = chooser.getMoves();

        // Assert
        assertThat(moves.size()).isEqualTo(1);
        assertThat(moves.get(1)).isEqualTo(1);
    }

    @Test
    public void should_return_the_first_move_option_when_has_more_that_one() {
        // Arrange
        int[] options = new int[] { -1, 0, 0, -1 };
        int[] moveOptions = new int[] { -1, 1, 10, -1 };

        Particle particle = new Particle(1, 0);
        particle.updateMoveOptions(options, moveOptions);

        HashMap<Integer, Particle> particles = new HashMap<>();
        particles.put(particle.id, particle);

        MovesChooser chooser = new MovesChooser(particles, 11);

        // Act
        HashMap<Integer, Integer> moves = chooser.getMoves();

        // Assert
        assertThat(moves.size()).isEqualTo(1);
        assertThat(moves.get(1)).isEqualTo(1);
    }

    @Test
    public void should_return_the_end_cell_when_she_is_a_option() {
        // Arrange
        int[] options01 = new int[] { 0, -1, 0, 0 };
        int[] moveOptions01 = new int[] { 3, -1, 11, 6 };
        Particle particle01 = new Particle(1, 0);
        particle01.updateMoveOptions(options01, moveOptions01);

        HashMap<Integer, Particle> particles = new HashMap<>();
        particles.put(particle01.id, particle01);

        MovesChooser chooser = new MovesChooser(particles, 11);

        // Act
        HashMap<Integer, Integer> moves = chooser.getMoves();

        // Assert
        assertThat(moves.size()).isEqualTo(1);
        assertThat(moves.get(1)).isEqualTo(11);
        assertThat(chooser.outs.get(0)).isEqualTo(1);
    }

    @Test
    public void should_return_duplicated_value_when_the_move_option_is_the_end_cell() {
        // Arrange
        int[] options01 = new int[] { 0, -1, 0, 0 };
        int[] moveOptions01 = new int[] { 3, -1, 11, 6 };
        Particle particle01 = new Particle(1, 0);
        particle01.updateMoveOptions(options01, moveOptions01);

        int[] options02 = new int[] { 0, 0, -1, 0 };
        int[] moveOptions02 = new int[] { 6, 11, 9 };
        Particle particle02 = new Particle(2, 1);
        particle02.updateMoveOptions(options02, moveOptions02);

        HashMap<Integer, Particle> particles = new HashMap<>();
        particles.put(particle01.id, particle01);
        particles.put(particle02.id, particle02);

        MovesChooser chooser = new MovesChooser(particles, 11);

        // Act
        HashMap<Integer, Integer> moves = chooser.getMoves();

        // Assert
        assertThat(moves.size()).isEqualTo(2);
        assertThat(moves.get(1)).isEqualTo(11);
        assertThat(moves.get(2)).isEqualTo(11);
        assertThat(chooser.outs.get(0)).isEqualTo(1);
        assertThat(chooser.outs.get(1)).isEqualTo(2);
    }

    @Test
    public void should_only_return_duplicated_value_when_the_move_option_is_the_end_cell() {
        // Arrange
        int[] options01 = new int[] { -1, 0, 0, -1 };
        int[] moveOptions01 = new int[] { -1, 1, 4, -1 };
        Particle particle01 = new Particle(2, 1);
        particle01.updateMoveOptions(options01, moveOptions01);

        int[] options02 = new int[] { 0, 0, 0, 0 };
        int[] moveOptions02 = new int[] { 1, 6, 9, 4 };
        Particle particle02 = new Particle(1, 0);
        particle02.updateMoveOptions(options02, moveOptions02);

        HashMap<Integer, Particle> particles = new HashMap<>();
        particles.put(particle01.id, particle01);
        particles.put(particle02.id, particle02);

        MovesChooser chooser = new MovesChooser(particles, 11);

        // Act
        HashMap<Integer, Integer> moves = chooser.getMoves();

        // Assert
        assertThat(moves.size()).isEqualTo(2);
        assertThat(moves.get(1) != moves.get(2)).isTrue();
    }
}
