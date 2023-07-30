package de.scrupy.bedwars.scoreboard;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.common.map.GameMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class LobbyScoreboard extends GameScoreboard {
    private static final String CURRENT_TEAM_NAME = "currentTeam";
    private static final String SCOREBOARD_PLACEHOLDER_KEY = "scoreboardPlaceholder";

    public LobbyScoreboard() {
        super();
        addTeams();
        setScores();
    }

    private void setScores() {
        getObjective().getScore("   ").setScore(6);
        getObjective().getScore(GameConfig.getInstance().getName("scoreboardCurrentTeam")).setScore(5);
        getObjective().getScore(ChatColor.BLACK + "").setScore(4);
        getObjective().getScore("  ").setScore(3);
        getObjective().getScore(GameConfig.getInstance().getName("scoreboardSelectedMap")).setScore(2);
        getObjective().getScore(getSelectedMapName()).setScore(1);
        getObjective().getScore(" ").setScore(0);
    }

    private String getSelectedMapName() {
        GameMap gameMap = Game.getInstance().getGameMap();
        if (gameMap != null) {
            return String.format(GameConfig.getInstance().getName("scoreboardSelectedMapName"), gameMap.getName());
        } else {
            return GameConfig.getInstance().getName(SCOREBOARD_PLACEHOLDER_KEY);
        }
    }

    private void addTeams() {
        org.bukkit.scoreboard.Team currentTeam = getScoreboard().registerNewTeam(CURRENT_TEAM_NAME);
        currentTeam.setPrefix(GameConfig.getInstance().getName(SCOREBOARD_PLACEHOLDER_KEY));
        currentTeam.addEntry(ChatColor.BLACK + "");
    }

    public static void updateTeam(Player player, de.scrupy.bedwars.team.Team team) {
        Team scoreboardTeam = player.getScoreboard().getTeam(CURRENT_TEAM_NAME);
        if (scoreboardTeam != null) {
            String prefix = String.format(GameConfig.getInstance().getName("scoreboardCurrentTeamName"), team.getColoredName());
            scoreboardTeam.setPrefix(prefix);
        }
    }
}
