package de.scrupy.bedwars.player;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.lobby.LobbyItem;
import de.scrupy.bedwars.scoreboard.LobbyScoreboard;
import de.scrupy.bedwars.team.TeamManager;
import de.scrupy.bedwars.util.PlayerAttributes;
import org.bukkit.entity.Player;

public class PlayerHandler {
    private final Game game;
    private final TeamManager teamManager;
    private final PlayerAttributes playerAttributes;

    public PlayerHandler(Game game, TeamManager teamManager) {
        this.game = game;
        this.teamManager = teamManager;
        this.playerAttributes = new PlayerAttributes(game);
    }

    public void handleJoin(Player player) {
        setupPlayer(player);
    }

    private void setupPlayer(Player player) {
        if (game.getGameState() == GameState.LOBBY) {
            LobbyScoreboard lobbyScoreboard = new LobbyScoreboard(game.getGameMap(), teamManager);
            lobbyScoreboard.setScoreboard(player);

            playerAttributes.setAttributes(player, false);
            LobbyItem.setLobbyItems(player);
        } else {
            playerAttributes.setAttributes(player, true);
        }
    }
}
