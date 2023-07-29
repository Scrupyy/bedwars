package de.scrupy.bedwars.player;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.lobby.LobbyItem;
import de.scrupy.bedwars.scoreboard.LobbyScoreboard;
import de.scrupy.bedwars.util.PlayerAttributes;
import org.bukkit.entity.Player;

public class PlayerHandler {
    public void handleJoin(Player player) {
        setupPlayer(player);
    }

    private void setupPlayer(Player player) {
        if (Game.getInstance().getGameState() == GameState.LOBBY) {
            LobbyScoreboard lobbyScoreboard = new LobbyScoreboard();
            lobbyScoreboard.setScoreboard(player);

            PlayerAttributes.setAttributes(player, false);
            LobbyItem.setLobbyItems(player);
        } else {
            PlayerAttributes.setAttributes(player, true);
        }
    }
}
