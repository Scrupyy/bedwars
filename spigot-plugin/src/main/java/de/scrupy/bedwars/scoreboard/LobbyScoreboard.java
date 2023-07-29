package de.scrupy.bedwars.scoreboard;

import de.scrupy.bedwars.config.GameConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LobbyScoreboard extends GameScoreboard {
    private static final String CURRENT_TEAM_NAME = "currentTeam";

    public LobbyScoreboard() {
        super();
        addTeams();
        setScores();
    }

    private void setScores() {
        getObjective().getScore("  ").setScore(3);
        getObjective().getScore(GameConfig.getInstance().getName("scoreboardCurrentTeam")).setScore(2);
        getObjective().getScore(ChatColor.BLACK + "").setScore(1);
        getObjective().getScore(" ").setScore(0);
    }

    private void addTeams() {
        org.bukkit.scoreboard.Team currentTeam = getScoreboard().registerNewTeam(CURRENT_TEAM_NAME);
        currentTeam.setPrefix("§4§lX");
        currentTeam.addEntry(ChatColor.BLACK + "");
    }

    public static void updateTeam(Player player, de.scrupy.bedwars.team.Team team) {
        org.bukkit.scoreboard.Team scoreboardTeam = player.getScoreboard().getTeam(CURRENT_TEAM_NAME);
        if (scoreboardTeam != null) {
            String prefix = String.format(GameConfig.getInstance().getName("scoreboardCurrentTeamName"), team.getColoredName());
            scoreboardTeam.setPrefix(prefix);
        }
    }
}
