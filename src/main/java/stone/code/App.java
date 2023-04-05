package stone.code;

import processing.core.PApplet;

public class App {
    static Mode mode = Mode.DEBUG;

    public static void main(String[] args) {
        if (mode == Mode.FUN) {
            funMode();
        }
        if (mode == Mode.DEBUG) {
            debugMode();
        }
        if (mode == Mode.RELEASE) {
            releaseMode();
        }
        if (mode == Mode.MAKER) {
            makerMode();
        }
    }

    public static void funMode() {
        PApplet.main(new String[] { "stone.GameFunMode" });
    }

    public static void debugMode() {
        PApplet.main(new String[] { "stone.code.GameDebugMode" });
    }

    public static void releaseMode() {
        // GameReleaseMode.run();
    }

    public static void makerMode() {
        PApplet.main(new String[] { "stone.MazeGenerator" });
    }
}
