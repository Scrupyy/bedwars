package de.scrupy.bedwars.team;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class PlayerTeamHandler {
    private final Map<Player, Team> playerTeams;

    public PlayerTeamHandler() {
        this.playerTeams = new HashMap<>();
    }

    public void addPlayerToTeam(Team team, Player player) {
        if (team.contains(player))
            return;
        removePlayerFromCurrentTeam(player);
        team.addPlayer(player);
        playerTeams.put(player, team);
    }

    @Nullable
    public Team getPlayerTeam(Player player) {
        return playerTeams.get(player);
    }

    private void removePlayerFromCurrentTeam(Player player) {
        if (playerTeams.containsKey(player)) {
            Team currentTeam = playerTeams.get(player);
            if (currentTeam != null) {
                currentTeam.removePlayer(player);
            }
        }
    }
}
