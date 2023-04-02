package stone;

import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PImage;

public class MazeGenerator extends PApplet {
    PImage image;
    boolean skipDraw = false;

    public void settings() {
        String fileName = "src/main/java/stone/images/monalisa.png";
        image = loadImage(fileName);
        size(image.width, image.height);
    }

    public void draw() {
        if (skipDraw) {
            return;
        }

        skipDraw = true;

        loadPixels();
        image.loadPixels();

        int[][] maze = new int[image.height][image.width];

        for (int y = 0; y < image.height; y++) {
            for (int x = 0; x < image.width; x++) {
                int loc = x + y * width;
                float r = red(image.pixels[loc]);
                int value = (r > 75) ? 0 : 1;
                maze[y][x] = value;
            }
        }

        maze[0][0] = 3;
        maze[image.height - 1][image.width - 1] = 4;

        ArrayList<String> output = new ArrayList<>();

        for (int row = 0; row < image.height; row++) {
            String str = Arrays.toString(maze[row]).replaceAll("[\\[\\],]", "").replace(" ", "");
            output.add(str);
        }

        String fileName = "src/main/java/stone/data/maze_04.txt";
        saveStrings(fileName, output.toArray(new String[0]));
    }
}
