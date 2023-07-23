package de.scrupy.bedwars.player;

import de.scrupy.bedwars.team.Team;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class GamePlayer {
    private final Player player;
    private Team team;

    public GamePlayer(Player player) {
        this.player = player;
    }

    public boolean joinedTeam() {
        return team == null;
    }

    public void joinTeam(Team team) {
        this.team = team;
    }

    @Nullable
    public Team getCurrentTeam() {
        return team;
    }
}
