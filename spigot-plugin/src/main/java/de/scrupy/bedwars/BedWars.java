package de.scrupy.bedwars;

import de.scrupy.bedwars.config.GameSettingsConfig;
import de.scrupy.bedwars.listener.*;
import de.scrupy.bedwars.player.PlayerHandler;
import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.TeamManager;
import de.scrupy.bedwars.team.TeamSelectionGui;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWars extends JavaPlugin {
    private static BedWars instance;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        //create dependencies
        PlayerHandler playerHandler = new PlayerHandler();
        TeamManager teamManager = new TeamManager();
        TeamSelectionGui teamSelectionGui = new TeamSelectionGui(teamManager);
        PlayerTeamHandler playerTeamHandler = new PlayerTeamHandler();

        // register listener
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(playerHandler), this);
        pluginManager.registerEvents(new PlayerQuitListener(playerTeamHandler, teamSelectionGui), this);
        pluginManager.registerEvents(new GameStateDependentListener(), this);
        pluginManager.registerEvents(new LobbyItemListener(teamSelectionGui), this);
        pluginManager.registerEvents(new TeamSelectionGuiListener(teamSelectionGui, playerTeamHandler), this);
        pluginManager.registerEvents(new PlayerJoinTeamListener(teamSelectionGui), this);
    }

    public static BedWars getInstance() {
        return instance;
    }
}
