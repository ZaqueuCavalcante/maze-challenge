package stone.code;

import java.util.ArrayList;
import java.util.HashMap;

public class MovesChooser {
    HashMap<Integer, Particle> particles;
    HashMap<Integer, Integer> moves;

    int endCellId;
    public ArrayList<Integer> outs;

    public MovesChooser(HashMap<Integer, Particle> particles, int endCellId) {
        this.particles = particles;
        moves = new HashMap<>();
        this.endCellId = endCellId;
        outs = new ArrayList<>();
    }

    public HashMap<Integer, Integer> getMoves() {
        for (Particle p : particles.values()) {
            for (int cellId : p.moveOptions) {
                if (cellId == endCellId) {
                    moves.put(p.id, cellId);
                    outs.add(p.id);
                    break;
                }
            }

            if (outs.contains(p.id)) {
                continue;
            }

            int cellId = p.moveOptions.get(0);
            moves.put(p.id, cellId);
        }

        return moves;
    }
}
