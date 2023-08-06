package de.scrupy.bedwars.scoreboard;

import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.Team;
import de.scrupy.bedwars.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerTablist {
    private static final String DEFAULT_TEAM_NAME = "defualt";
    private final Scoreboard scoreboard;
    private final TeamManager teamManager;
    private final PlayerTeamHandler playerTeamHandler;

    public PlayerTablist(Scoreboard scoreboard, TeamManager teamManager, PlayerTeamHandler playerTeamHandler) {
        this.scoreboard = scoreboard;
        this.teamManager = teamManager;
        this.playerTeamHandler = playerTeamHandler;
        registerTablistTeams();
        updateAllTablistPlayer();
    }

    private void registerTablistTeams() {
        teamManager.getAvailableTeams().forEach(team -> {
            scoreboard.registerNewTeam(team.getName()).setPrefix(team.getColoredName() + " §8| ");
        });
        scoreboard.registerNewTeam(DEFAULT_TEAM_NAME).setPrefix("§4§lX §8| ");
    }

    private void updateAllTablistPlayer() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            Team playerTeam = playerTeamHandler.getPlayerTeam(player);
            if (playerTeam != null) {
                addPlayerToTablistTeam(scoreboard, playerTeam.getName(), player);
            } else {
                addPlayerToTablistTeam(scoreboard, DEFAULT_TEAM_NAME, player);
            }
        });
    }

    public static void updatePlayerTablistTeam(Team team, Player player) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            Scoreboard scoreboard = p.getScoreboard();
            addPlayerToTablistTeam(scoreboard, team.getName(), player);
        });
    }

    private static void addPlayerToTablistTeam(Scoreboard scoreboard, String teamName, Player player) {
        org.bukkit.scoreboard.Team scoreboardTeam = scoreboard.getTeam(teamName);
        if (scoreboardTeam != null) {
            scoreboardTeam.addEntry(player.getName());
        } else {
            org.bukkit.scoreboard.Team defaultTeam = scoreboard.getTeam(DEFAULT_TEAM_NAME);
            if (defaultTeam != null)
                defaultTeam.addEntry(player.getName());
        }
    }
}
