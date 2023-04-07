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

    private void calculateMovesRightAndDow() {
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

            if (p.getEmpties() == 1) {
                int cellId = -1;
                for (int id : p.moveOptions) {
                    if (id != -1) {
                        cellId = id;
                        break;
                    }
                }

                if (aux.containsKey(cellId)) {
                    System.out.println("Stucked | id = " + p.id + " | (" + p.row + ", " + p.column + ")");
                }

                moves.put(p.id, cellId);
                aux.put(cellId, p.id);
                alreadyChoose.add(p.id);
            }
        }

        // Demais
        for (Particle p : particles.values()) {
            if (alreadyChoose.contains(p.id)) {
                continue;
            }

            int cellId = -1;

            if (p.canMoveRight() && p.canMoveDown()) {
                boolean x = Math.random() > 0.50;
                if (x) {
                    cellId = p.moveOptions[1];
                } else {
                    cellId = p.moveOptions[2];
                }
            } else if (p.canMoveRight()) {
                cellId = p.moveOptions[1];
            } else if (p.canMoveDown()) {
                cellId = p.moveOptions[2];
            }

            if (cellId == -1) {
                if (p.canMoveUp() && p.canMoveLeft()) {
                    boolean x = Math.random() > 0.50;
                    if (x) {
                        cellId = p.moveOptions[0];
                    } else {
                        cellId = p.moveOptions[3];
                    }
                } else if (p.canMoveUp()) {
                    cellId = p.moveOptions[0];
                } else if (p.canMoveLeft()) {
                    cellId = p.moveOptions[3];
                }
            }

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

            if (p.getEmpties() == 1) {
                int cellId = -1;
                for (int id : p.moveOptions) {
                    if (id != -1) {
                        cellId = id;
                        break;
                    }
                }

                if (aux.containsKey(cellId)) {
                    System.out.println("Stucked | id = " + p.id + " | (" + p.row + ", " + p.column + ")");
                }

                moves.put(p.id, cellId);
                aux.put(cellId, p.id);
                alreadyChoose.add(p.id);
            }
        }

        // Demais
        for (Particle p : particles.values()) {
            if (alreadyChoose.contains(p.id)) {
                continue;
            }

            int cellId = -1;
            while (cellId == -1) {
                int index = (int) (Math.random() * 4);
                cellId = p.moveOptions[index];
            }

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
        calculateMovesRightAndDow();

        while (!findSolution) {
            reset();
            calculateMoves();
        }

        return moves;
    }
}
