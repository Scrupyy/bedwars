package de.scrupy.bedwars.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTeamPicker {
    private final PlayerTeamHandler playerTeamHandler;
    private final TeamManager teamManager;
    private final Random random;

    public RandomTeamPicker(PlayerTeamHandler playerTeamHandler, TeamManager teamManager) {
        this.playerTeamHandler = playerTeamHandler;
        this.teamManager = teamManager;
        this.random = new Random();
    }

    public void pickRandomTeamForPlayersWithoutTeam() {
        List<Team> availableTeams = getAvailableTeams();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Team team = playerTeamHandler.getPlayerTeam(player);
            if (team == null && !availableTeams.isEmpty()) {
                int randomIndex = random.nextInt(availableTeams.size());
                Team availableTeam = availableTeams.get(randomIndex);
                playerTeamHandler.addPlayerToTeam(availableTeam, player);
                if (isTeamFilled(availableTeam))
                    availableTeams.remove(randomIndex);
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
        return team.getPlayers().size() == team.getMaxPlayers();
    }
}
