package stone.code;

import processing.core.PApplet;
import stone.code.games.GameReleaseMode;
import stone.code.games.GameResizerMode;
import stone.code.games.Mode;

public class App {
    static Mode mode = Mode.REPLAY;

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
        if (mode == Mode.RESIZER) {
            resizerMode();
        }
        if (mode == Mode.REPLAY) {
            replayMode();
        }
    }

    public static void funMode() {
        PApplet.main(new String[] { "stone.GameFunMode" });
    }

    public static void debugMode() {
        PApplet.main(new String[] { "stone.code.games.GameDebugMode" });
    }

    public static void releaseMode() {
        GameReleaseMode.run();
    }

    public static void resizerMode() {
        GameResizerMode.run();
    }

    public static void makerMode() {
        PApplet.main(new String[] { "stone.MazeGenerator" });
    }

    public static void replayMode() {
        PApplet.main(new String[] { "stone.code.games.GameReplayMode" });
    }
}
