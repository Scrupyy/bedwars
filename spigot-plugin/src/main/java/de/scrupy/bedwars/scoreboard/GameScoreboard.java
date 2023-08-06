package de.scrupy.bedwars.scoreboard;

import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public abstract class GameScoreboard {
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final PlayerTablist playerTablist;

    public GameScoreboard(TeamManager teamManager, PlayerTeamHandler playerTeamHandler) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("scorboard", Criteria.DUMMY, GameConfig.getInstance().getName("scoreboardTitle"));
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.playerTablist = new PlayerTablist(scoreboard, teamManager, playerTeamHandler);
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
