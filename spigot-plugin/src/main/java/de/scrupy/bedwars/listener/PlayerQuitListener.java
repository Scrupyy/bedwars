package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.scoreboard.GameScoreboard;
import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.TeamSelectionGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final PlayerTeamHandler playerTeamHandler;
    private final TeamSelectionGui teamSelectionGui;

    public PlayerQuitListener(PlayerTeamHandler playerTeamHandler, TeamSelectionGui teamSelectionGui) {
        this.playerTeamHandler = playerTeamHandler;
        this.teamSelectionGui = teamSelectionGui;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent playerQuitEvent) {
        Player player = playerQuitEvent.getPlayer();

        if (Game.getInstance().getGameState() == GameState.LOBBY) {
            playerTeamHandler.removePlayerFromTeam(player);
            teamSelectionGui.updateGui();
        }
    }
}
