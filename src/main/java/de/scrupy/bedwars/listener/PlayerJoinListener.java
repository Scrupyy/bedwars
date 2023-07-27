package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.player.PlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final PlayerHandler playerHandler;

    public PlayerJoinListener(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        String playerName = player.getName();

        playerHandler.handleJoin(player);

        if (Game.getInstance().getGameState() == GameState.LOBBY) {
            joinEvent.setJoinMessage(GameConfig.getInstance().getMessage("playerJoinMessage", playerName));
        }
    }
}
