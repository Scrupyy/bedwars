package de.scrupy.bedwars.team;

import de.scrupy.bedwars.event.PlayerJoinTeamEvent;
import org.bukkit.Bukkit;
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
        removePlayerFromTeam(player);
        team.addPlayer(player);
        playerTeams.put(player, team);
        PlayerJoinTeamEvent playerJoinTeamEvent = new PlayerJoinTeamEvent(player, team);
        Bukkit.getPluginManager().callEvent(playerJoinTeamEvent);
    }

    public void removePlayerFromTeam(Player player) {
        if (playerTeams.containsKey(player)) {
            Team currentTeam = playerTeams.get(player);
            if (currentTeam != null) {
                currentTeam.removePlayer(player);
            }
        }
    }

    @Nullable
    public Team getPlayerTeam(Player player) {
        return playerTeams.get(player);
    }
}
