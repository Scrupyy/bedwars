package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.event.GameStateChangeEvent;
import de.scrupy.bedwars.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStateChangeListener implements Listener {
    private final PlayerHandler playerHandler;

    public GameStateChangeListener(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    @EventHandler
    public void onGameStateChange(GameStateChangeEvent event) {
        Bukkit.getOnlinePlayers().forEach(player -> playerHandler.handleGameStateChange(player, event.getNewState()));
    }
}
