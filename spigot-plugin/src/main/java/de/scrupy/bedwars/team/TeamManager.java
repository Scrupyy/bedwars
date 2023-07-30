package de.scrupy.bedwars.team;

import de.scrupy.bedwars.config.GameSettingsConfig;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamManager {
    private final List<Team> teams;
    private final List<Team> availableTeams;

    private static final String TEAM_AMOUNT_KEY = "teamAmount";
    private static final String TEAM_PLAYERS_KEY = "teamPlayers";
    private static final int DEFAULT_TEAM_AMOUNT = 4;
    private static final int DEFAULT_TEAM_PLAYERS = 1;

    public TeamManager() {
        this.teams = new ArrayList<>();
        this.availableTeams = new ArrayList<>();
        initializeTeam();
        initializeAvailableTeams();
    }

    private void initializeTeam() {
        teams.add(new Team("Red", ChatColor.RED, Material.RED_WOOL));
        teams.add(new Team("Blue", ChatColor.BLUE, Material.BLUE_WOOL));
        teams.add(new Team("Green", ChatColor.GREEN, Material.GREEN_WOOL));
        teams.add(new Team("Yellow", ChatColor.YELLOW, Material.YELLOW_WOOL));
        teams.add(new Team("Purple", ChatColor.DARK_PURPLE, Material.PURPLE_WOOL));
        teams.add(new Team("Cyan", ChatColor.AQUA, Material.CYAN_WOOL));
        teams.add(new Team("Pink", ChatColor.LIGHT_PURPLE, Material.PINK_WOOL));
        teams.add(new Team("White", ChatColor.WHITE, Material.WHITE_WOOL));
    }

    private void initializeAvailableTeams() {
        int teamAmount = GameSettingsConfig.getInstance().getInteger(TEAM_AMOUNT_KEY);
        int teamPlayers = GameSettingsConfig.getInstance().getInteger(TEAM_PLAYERS_KEY);

        if (teamAmount < 2 || teamAmount > 8) {
            teamAmount = DEFAULT_TEAM_AMOUNT;
            Bukkit.getLogger().warning("Team amount must to be between 2 and 8. Team amount was set to default value of: 4");
        }

        if (teamPlayers < 1) {
            teamPlayers = DEFAULT_TEAM_PLAYERS;
            Bukkit.getLogger().warning("Team players count must be at least 1. Team players count set to default value of 1.");
        }

        for (int i = 0; i < teamAmount; i++) {
            Team team = teams.get(i);
            team.setMaxPlayers(teamPlayers);
            availableTeams.add(team);
        }
    }

    public List<Team> getAvailableTeams() {
        return Collections.unmodifiableList(availableTeams);
    }
}
