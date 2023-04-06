package stone.code.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;

public class GameResizerMode {
    public static void run() {
        final int size = 10;
        int[][] newMaze = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                newMaze[row][column] = (Math.random() < 0.17) ? 1 : 0;
            }
        }

        newMaze[0][0] = 3;
        newMaze[size - 1][size - 1] = 4;

        ArrayList<String> output = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            String str = Arrays.toString(newMaze[row]).replaceAll("[\\[\\],]", "");
            output.add(str);
        }

        String fileName = "src/main/java/stone/code/mazes/maze_05_sinuca_" + size + "x" + size + ".txt";
        File file = new File(fileName);
        OutputStream outputStream;

        try {
            outputStream = new FileOutputStream(file);
            PApplet.saveStrings(outputStream, output.toArray(new String[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
