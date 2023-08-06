package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.event.PlayerJoinTeamEvent;
import de.scrupy.bedwars.scoreboard.GameScoreboard;
import de.scrupy.bedwars.scoreboard.LobbyScoreboard;
import de.scrupy.bedwars.team.TeamSelectionGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinTeamListener implements Listener {
    private final TeamSelectionGui teamSelectionGui;

    public PlayerJoinTeamListener(TeamSelectionGui teamSelectionGui) {
        this.teamSelectionGui = teamSelectionGui;
    }

    @EventHandler
    public void onTeamJoin(PlayerJoinTeamEvent event) {
        teamSelectionGui.updateGui();

        LobbyScoreboard.updateTeam(event.getPlayer(), event.getTeam());
        GameScoreboard.updatePlayerTablistTeam(event.getTeam(), event.getPlayer());
    }
}
