package stone;

import processing.core.PApplet;

public class App {
    static Mode mode = Mode.RELEASE;

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
    }

    public static void funMode() {
        PApplet.main(new String[] { "stone.GameFunMode" });
    }

    public static void debugMode() {
        PApplet.main(new String[] { "stone.GameDebugMode" });
    }

    public static void releaseMode() {
        GameReleaseMode.run();
    }
}
