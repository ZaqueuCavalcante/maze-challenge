package stone.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MovesChooser {
    HashMap<Integer, Particle> particles;

    // ParticleId -> CellId
    HashMap<Integer, Integer> moves;

    // CellId -> ParticleId
    HashMap<Integer, Integer> aux;

    int endCellId;

    // Particles on end cell
    public ArrayList<Integer> outs;

    // Particles that already choose yours move
    public HashSet<Integer> alreadyChoose;

    public MovesChooser(HashMap<Integer, Particle> particles, int endCellId) {
        this.particles = particles;
        this.endCellId = endCellId;

        moves = new HashMap<>();
        aux = new HashMap<>();
        outs = new ArrayList<>();
        alreadyChoose = new HashSet<>();
    }

    private void reset() {
        moves.clear();
        aux.clear();
        outs.clear();
        alreadyChoose.clear();
    }

    private void calculateMoves() {
        // 0, 1 ou 2 que tem a opcao de ir pro end
        for (Particle p : particles.values()) {
            for (int cellId : p.moveOptions) {
                if (cellId == endCellId) {
                    moves.put(p.id, cellId);
                    aux.put(cellId, p.id);
                    alreadyChoose.add(p.id);
                    outs.add(p.id);
                    break;
                }
            }
        }

        // Todas com so UMA opcao
        for (Particle p : particles.values()) {
            if (alreadyChoose.contains(p.id)) {
                continue;
            }

            if (p.moveOptions.size() == 1) {
                final int cellId = p.moveOptions.get(0);

                if (aux.containsKey(cellId)) {
                    System.out.println("Stucked | id = " + p.id + " | (" + p.row + ", " + p.column + ")");
                }

                moves.put(p.id, cellId);
                aux.put(cellId, p.id);
                alreadyChoose.add(p.id);
            }
        }

        for (Particle p : particles.values()) {
            if (alreadyChoose.contains(p.id)) {
                continue;
            }

            int index = (int) (Math.random() * p.moveOptions.size());
            int cellId = p.moveOptions.get(index);

            if (aux.containsKey(cellId)) {
                // System.out.println("Random Stucked | id = " + p.id + " | (" + p.row + ", " +
                // p.column + ")");
                return;
            }

            moves.put(p.id, cellId);
            aux.put(cellId, p.id);
            alreadyChoose.add(p.id);
        }

        findSolution = true;
    }

    boolean findSolution = false;

    public HashMap<Integer, Integer> getMoves() {
        while (!findSolution) {
            reset();
            calculateMoves();
        }

        return moves;
    }
}
