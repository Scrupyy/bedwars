package de.scrupy.bedwars.scoreboard;

import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.Team;
import de.scrupy.bedwars.team.TeamManager;
import org.bukkit.Bukkit;

public class IngameScoreboard extends GameScoreboard {
    public IngameScoreboard(TeamManager teamManager, PlayerTeamHandler playerTeamHandler) {
        super(teamManager, playerTeamHandler);
        addScoreboardTeams(teamManager);
        setScores(teamManager);
    }

    private void setScores(TeamManager teamManager) {
        int index = 1;
        getObjective().getScore("").setScore(0);
        for (Team team : teamManager.getAvailableTeams()) {
            getObjective().getScore(team.getColor() + "").setScore(index);
            index++;
        }
        getObjective().getScore(" ").setScore(index);
    }

    private void addScoreboardTeams(TeamManager teamManager) {
        for (Team team : teamManager.getAvailableTeams()) {
            org.bukkit.scoreboard.Team scoreboardTeam = getScoreboard().registerNewTeam("scoreboard_" + team.getName());
            scoreboardTeam.setPrefix(team.getColoredName());
            scoreboardTeam.setSuffix(" §a§l✓");
            scoreboardTeam.addEntry(team.getColor() + "");
        }
    }

    public static void updateTeamSuffix(Team team) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            org.bukkit.scoreboard.Team scoreboardTeam = player.getScoreboard().getTeam("scoreboard_" + team.getName());
            if (scoreboardTeam != null)
                scoreboardTeam.setSuffix(" §4§lX");
        });
    }
}
