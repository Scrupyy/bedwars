package de.scrupy.bedwars;

import de.scrupy.bedwars.config.GameSettingsConfig;
import de.scrupy.bedwars.map.MapLoader;
import de.scrupy.common.map.GameMap;
import org.bukkit.Bukkit;

public class Game {
    private static Game instance;
    private final GameSettingsConfig gameSettingsConfig;
    private GameState gameState;
    private GameMap gameMap;

    private Game() {
        this.gameState = GameState.LOBBY;
        this.gameSettingsConfig = GameSettingsConfig.getInstance();
        loadMap();
    }

    private void loadMap() {
        MapLoader mapLoader = new MapLoader();

        String mapName = gameSettingsConfig.getString("mapName");
        int teamAmount = gameSettingsConfig.getInteger("teamAmount");
        int teamPlayers = gameSettingsConfig.getInteger("teamPlayers");

        GameMap gameMap = mapLoader.loadMap(mapName, teamAmount, teamPlayers);
        if (gameMap != null) {
            this.gameMap = gameMap;
        } else {
            Bukkit.getLogger().warning("Map could not be loaded. Check config.yml file for correct configuration of mapName, teamAmount and teamPlayers");
        }
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

    public GameMap getGameMap() {
        return gameMap;
    }

    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
}
