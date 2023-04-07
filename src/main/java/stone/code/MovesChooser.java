package stone.code;

import java.util.HashMap;

public class MovesChooser {
    HashMap<Integer, Particle> particles;
    HashMap<Integer, Integer> moves;

    int endCellId;

    public MovesChooser(HashMap<Integer, Particle> particles, int endCellId) {
        this.particles = particles;
        moves = new HashMap<>();
        this.endCellId = endCellId;
    }

    public HashMap<Integer, Integer> getMoves() {
        for (Particle p : particles.values()) {
            boolean hasEndAsOption = false;
            for (int cellId : p.moveOptions) {
                if (cellId == endCellId) {
                    moves.put(p.id, cellId);
                    hasEndAsOption = true;
                    break;
                }
            }

            if (hasEndAsOption) {
                continue;
            }

            int cellId = p.moveOptions.get(0);
            moves.put(p.id, cellId);
        }

        return moves;
    }
}
