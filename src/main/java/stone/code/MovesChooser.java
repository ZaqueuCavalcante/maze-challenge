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
        // Todas com so UMA opcao


        // 0, 1 ou 2 que tem a opcao de ir pro end



        // Salvar estado ate aqui e ir tentando combinacoes ate resolver


        // Fica mais facil iterar sobre as celulas q sobre a particulas?



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
