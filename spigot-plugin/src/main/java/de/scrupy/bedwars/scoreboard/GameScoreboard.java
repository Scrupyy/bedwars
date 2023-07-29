package de.scrupy.bedwars.scoreboard;

import de.scrupy.bedwars.config.GameConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public abstract class GameScoreboard {
    private final Scoreboard scoreboard;
    private final Objective objective;

    public GameScoreboard() {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("scorboard", Criteria.DUMMY, GameConfig.getInstance().getName("scoreboardTitle"));
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
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
