package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.Team;
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

        Team team = playerTeamHandler.getPlayerTeam(player);
        if (team != null) {
            team.removePlayer(player);
            teamSelectionGui.updateGui();
        }
    }
}
