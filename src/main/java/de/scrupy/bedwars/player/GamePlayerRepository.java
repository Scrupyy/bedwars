package de.scrupy.bedwars.player;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class GamePlayerRepository {
    private final Map<Player, GamePlayer> gamePlayers;

    public GamePlayerRepository() {
        this.gamePlayers = new HashMap<>();
    }

    public void addPlayer(Player player) {
        GamePlayer gamePlayer = new GamePlayer(player);
        gamePlayers.put(player, gamePlayer);
    }

    public void removePlayer(Player player) {
        this.gamePlayers.remove(player);
    }

    @Nullable
    public GamePlayer getGamePlayer(Player player) {
        return gamePlayers.get(player);
    }

    public boolean contains(Player player) {
        return gamePlayers.containsKey(player);
    }

    public void clear() {
        gamePlayers.clear();
    }
}
