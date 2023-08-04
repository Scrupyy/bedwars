package de.scrupy.bedwars.map;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.team.Team;
import de.scrupy.bedwars.team.TeamManager;
import de.scrupy.common.map.GameMap;
import de.scrupy.common.map.GameMapLocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTeleport {
    private final TeamManager teamManager;
    private final GameMap gameMap;

    public MapTeleport(TeamManager teamManager, GameMap gameMap) {
        this.teamManager = teamManager;
        this.gameMap = gameMap;
    }

    public void teleportAllTeams() {
        if (gameMap == null) {
            throw new IllegalStateException("GameMap has not been initialized.");
        }

        List<Team> teams = new ArrayList<>(teamManager.getAvailableTeams());
        Map<String, GameMapLocation> teamSpawns = new HashMap<>(gameMap.getTeamSpawns());

        teleportTeamsToSpawnByColor(teams, teamSpawns);
        teleportTeamsToRandomSpawn(teams, teamSpawns);
    }

    private void teleportTeamsToSpawnByColor(List<Team> teams, Map<String, GameMapLocation> teamSpawns) {
        List<Team> teleportedTeams = new ArrayList<>();

        for (Team team : teams) {
            String woolColorName = team.getColoredWool().name();
            GameMapLocation gameMapLocation = gameMap.getTeamSpawns().get(woolColorName);
            if (gameMapLocation != null) {
                teleportAllTeamPlayers(team, gameMapLocation);
                teleportedTeams.add(team);
            }
        }

        teams.removeAll(teleportedTeams);
        teleportedTeams.forEach(team -> teamSpawns.remove(team.getColoredWool().name()));
    }

    private void teleportTeamsToRandomSpawn(List<Team> teams, Map<String, GameMapLocation> teamSpawns) {
        List<Team> teleportedTeams = new ArrayList<>();

        for (Team team : teams) {
            Map.Entry<String, GameMapLocation> mapEntry = teamSpawns.entrySet().iterator().next();
            if (mapEntry != null) {
                GameMapLocation gameMapLocation = mapEntry.getValue();
                teleportAllTeamPlayers(team, gameMapLocation);
                teleportedTeams.add(team);
            }
        }

        teams.removeAll(teleportedTeams);
        teleportedTeams.forEach(team -> teamSpawns.remove(team.getColoredWool().name()));
    }

    private void teleportAllTeamPlayers(Team team, GameMapLocation gameMapLocation) {
        Location teamSpawnLocation = GameMapLocationConverter.toLocation(gameMapLocation);
        for (Player player : team.getPlayers()) {
            player.teleport(teamSpawnLocation);
        }
    }
}
