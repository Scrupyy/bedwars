package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.BedWars;
import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.player.PlayerHandler;
import de.scrupy.bedwars.scoreboard.LobbyScoreboard;
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
        joinEvent.setJoinMessage(BedWars.getInstance().getPrefix() + "§a" + playerName + " §7joined the game.");
    }
}
