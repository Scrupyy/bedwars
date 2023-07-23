package de.scrupy.bedwars;

public class Game {
    private static Game instance;
    private GameState gameState;

    private Game() {
        this.gameState = GameState.LOBBY;
    }

    public void startGame() {
        if (gameState != GameState.LOBBY) {
            return;
        }

        gameState = GameState.INGAME;
    }

    public GameState getGameState() {
        return gameState;
    }

    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
