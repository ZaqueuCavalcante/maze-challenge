package stone.code;

import java.util.HashMap;

public class MovesChooser {
    HashMap<Integer, Particle> particles;
    HashMap<Integer, Integer> moves;

    public MovesChooser(HashMap<Integer, Particle> particles) {
        this.particles = particles;
        moves = new HashMap<>();
    }

    public HashMap<Integer, Integer> getMoves() {
        // Write code here lalala

        for (Particle p : particles.values()) {
            int cellId = p.moveOptions.get(0);
            moves.put(p.id, cellId);
        }

        return moves;
    }
}
