package de.scrupy.bedwars.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public abstract class GameScoreboard {
    private final Scoreboard scoreboard;
    private final Objective objective;

    public GameScoreboard() {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("scorboard", Criteria.DUMMY, "Scoreboard");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        initializeScoreboardValues();
    }

    public abstract void initializeScoreboardValues();

    protected Objective getObjective() {
        return objective;
    }

    public void setScoreboard(Player player) {
        player.setScoreboard(this.scoreboard);
    }
}
