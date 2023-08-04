package de.scrupy.bedwars;

import de.scrupy.bedwars.config.GameSettingsConfig;
import de.scrupy.bedwars.countdown.LobbyCountdown;
import de.scrupy.bedwars.map.MapLoader;
import de.scrupy.bedwars.map.MapTeleport;
import de.scrupy.bedwars.map.MaterialSpawnerManager;
import de.scrupy.bedwars.team.TeamManager;
import de.scrupy.bedwars.util.Countdown;
import de.scrupy.common.map.GameMap;
import org.bukkit.Bukkit;

import javax.annotation.Nullable;

public class Game {
    private final GameSettingsConfig gameSettingsConfig;
    private final TeamManager teamManager;
    private GameState gameState;
    private GameMap gameMap;
    private Countdown countdown;

    public Game(TeamManager teamManager) {
        this.teamManager = teamManager;
        this.gameState = GameState.LOBBY;
        this.gameSettingsConfig = GameSettingsConfig.getInstance();
        this.gameMap = loadMap();
        this.countdown = new LobbyCountdown(gameSettingsConfig.getInteger("lobbyCountdownTime"), this);
    }

    private GameMap loadMap() {
        MapLoader mapLoader = new MapLoader();

        String mapName = gameSettingsConfig.getString("mapName");
        int teamAmount = gameSettingsConfig.getInteger("teamAmount");
        int teamPlayers = gameSettingsConfig.getInteger("teamPlayers");

        GameMap gameMap = mapLoader.loadMap(mapName, teamAmount, teamPlayers);
        if (gameMap != null) {
            return gameMap;
        } else {
            Bukkit.getLogger().warning("Map could not be loaded. Check config.yml file for correct configuration of mapName, teamAmount and teamPlayers");
            return null;
        }
    }

    public void startGame() {
        if (gameState != GameState.LOBBY) {
            return;
        }

        gameState = GameState.INGAME;

        MapTeleport mapTeleport = new MapTeleport(teamManager, gameMap);
        mapTeleport.teleportAllTeams();

        MaterialSpawnerManager materialSpawnerManager = new MaterialSpawnerManager(gameMap);
        materialSpawnerManager.startSpawner();
    }

    public GameState getGameState() {
        return gameState;
    }

    @Nullable
    public GameMap getGameMap() {
        return gameMap;
    }

    public Countdown getCountdown() {
        return countdown;
    }
}
