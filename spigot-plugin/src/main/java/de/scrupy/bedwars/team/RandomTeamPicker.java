package de.scrupy.bedwars.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RandomTeamPicker {
    private final PlayerTeamHandler playerTeamHandler;
    private final TeamManager teamManager;

    public RandomTeamPicker(PlayerTeamHandler playerTeamHandler, TeamManager teamManager) {
        this.playerTeamHandler = playerTeamHandler;
        this.teamManager = teamManager;
    }

    public void pickRandomTeamForPlayersWithoutTeam() {
        List<Team> availableTeams = getAvailableTeams();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Team team = playerTeamHandler.getPlayerTeam(player);
            if (team == null && !availableTeams.isEmpty()) {
                Iterator<Team> teamIterator = availableTeams.iterator();
                Team nextAvailableTeam = teamIterator.next();
                playerTeamHandler.addPlayerToTeam(nextAvailableTeam, player);
                if (isTeamFilled(nextAvailableTeam))
                    teamIterator.remove();
            }
        }
    }

    private List<Team> getAvailableTeams() {
        List<Team> freeTeams = new ArrayList<>();
        for (Team team : teamManager.getAvailableTeams()) {
            if (!isTeamFilled(team))
                freeTeams.add(team);
        }
        return freeTeams;
    }

    private boolean isTeamFilled(Team team) {
        return team.getPlayers().size() < team.getMaxPlayers();
    }
}
