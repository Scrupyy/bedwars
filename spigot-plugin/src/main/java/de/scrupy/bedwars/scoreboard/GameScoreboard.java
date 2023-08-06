package de.scrupy.bedwars.scoreboard;

import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.Team;
import de.scrupy.bedwars.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public abstract class GameScoreboard {
    private static final String DEFAULT_TEAM_NAME = "default";
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final TeamManager teamManager;
    private final PlayerTeamHandler playerTeamHandler;

    public GameScoreboard(TeamManager teamManager, PlayerTeamHandler playerTeamHandler) {
        this.teamManager = teamManager;
        this.playerTeamHandler = playerTeamHandler;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("scorboard", Criteria.DUMMY, GameConfig.getInstance().getName("scoreboardTitle"));
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
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
                addPlayerToScoreboardTeam(scoreboard, playerTeam.getName(), player);
            } else {
                addPlayerToScoreboardTeam(scoreboard, DEFAULT_TEAM_NAME, player);
            }
        });
    }

    public static void updatePlayerTablistTeam(Team team, Player player) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            Scoreboard scoreboard = p.getScoreboard();
            addPlayerToScoreboardTeam(scoreboard, team.getName(), player);
        });
    }

    private static void addPlayerToScoreboardTeam(Scoreboard scoreboard, String teamName, Player player) {
        org.bukkit.scoreboard.Team scoreboardTeam = scoreboard.getTeam(teamName);
        if (scoreboardTeam != null) {
            scoreboardTeam.addEntry(player.getName());
        } else {
            org.bukkit.scoreboard.Team defaultTeam = scoreboard.getTeam(DEFAULT_TEAM_NAME);
            if (defaultTeam != null)
                defaultTeam.addEntry(player.getName());
        }
    }

    protected Objective getObjective() {
        return objective;
    }

    protected Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Player player) {
        player.setScoreboard(this.scoreboard);
    }
}
