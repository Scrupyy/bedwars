package de.scrupy.bedwars.player;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.lobby.LobbyItem;
import de.scrupy.bedwars.scoreboard.LobbyScoreboard;
import de.scrupy.bedwars.team.TeamManager;
import de.scrupy.bedwars.util.PlayerAttributes;
import org.bukkit.entity.Player;

public class PlayerHandler {
    private final TeamManager teamManager;

    public PlayerHandler(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void handleJoin(Player player) {
        setupPlayer(player);
    }

    private void setupPlayer(Player player) {
        if (Game.getInstance().getGameState() == GameState.LOBBY) {
            LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(teamManager);
            lobbyScoreboard.setScoreboard(player);

            PlayerAttributes.setAttributes(player, false);
            LobbyItem.setLobbyItems(player);
        } else {
            PlayerAttributes.setAttributes(player, true);
        }
    }
}
