package de.scrupy.bedwars.scoreboard;

import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.team.Team;
import de.scrupy.bedwars.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public abstract class GameScoreboard {
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final TeamManager teamManager;

    public GameScoreboard(TeamManager teamManager) {
        this.teamManager = teamManager;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("scorboard", Criteria.DUMMY, GameConfig.getInstance().getName("scoreboardTitle"));
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    private void registerTeams() {
        teamManager.getAvailableTeams().forEach(team -> {
            scoreboard.registerNewTeam(team.getName());
        });
    }

    public static void updateScoreboardTeam(Team team, Player player) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            Scoreboard scoreboard = p.getScoreboard();
            org.bukkit.scoreboard.Team scoreboardTeam = scoreboard.getTeam(team.getName());
            if (scoreboardTeam != null)
                scoreboardTeam.addEntry(player.getName());
        });
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
