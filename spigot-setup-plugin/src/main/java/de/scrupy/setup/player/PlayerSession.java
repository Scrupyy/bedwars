package de.scrupy.setup.player;

import de.scrupy.common.map.GameMap;
import de.scrupy.setup.map.GameMapBuilder;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class PlayerSession {
    private final Map<Player, GameMap> playerSessions;

    public PlayerSession() {
        this.playerSessions = new HashMap<>();
    }

    public void put(Player player, GameMap gameMap) {
        playerSessions.put(player, gameMap);
    }

    @Nullable
    public GameMap getGameMap(Player player) {
        return playerSessions.get(player);
    }
}
