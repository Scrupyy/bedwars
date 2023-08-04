package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.config.GameSettingsConfig;
import de.scrupy.bedwars.player.PlayerHandler;
import de.scrupy.bedwars.util.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final Game game;
    private final PlayerHandler playerHandler;

    public PlayerJoinListener(Game game, PlayerHandler playerHandler) {
        this.game = game;
        this.playerHandler = playerHandler;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        String playerName = player.getName();
        joinEvent.setJoinMessage(null);

        playerHandler.handleJoin(player);

        if (game.getGameState() == GameState.LOBBY) {
            Bukkit.broadcastMessage(GameConfig.getInstance().getMessage("playerJoinMessage", playerName));

            if (!game.getCountdown().isRunning() && shouldStartLobbyCountdown()) {
                game.getCountdown().start();
            }
        }
    }

    public boolean shouldStartLobbyCountdown() {
        int playersRequired = GameSettingsConfig.getInstance().getInteger("playersToStartGame");
        return Bukkit.getOnlinePlayers().size() >= playersRequired;
    }
}
