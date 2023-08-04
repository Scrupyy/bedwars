package de.scrupy.bedwars;

import de.scrupy.bedwars.listener.*;
import de.scrupy.bedwars.player.PlayerHandler;
import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.TeamManager;
import de.scrupy.bedwars.team.TeamSelectionGui;
import de.scrupy.bedwars.util.PlayerAttributes;
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
        TeamManager teamManager = new TeamManager();
        Game game = new Game(teamManager);
        PlayerHandler playerHandler = new PlayerHandler(game, teamManager);
        TeamSelectionGui teamSelectionGui = new TeamSelectionGui(teamManager);
        PlayerTeamHandler playerTeamHandler = new PlayerTeamHandler();

        // register listener
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(game, playerHandler), this);
        pluginManager.registerEvents(new PlayerQuitListener(game, playerTeamHandler, teamSelectionGui), this);
        pluginManager.registerEvents(new GameStateDependentListener(), this);
        pluginManager.registerEvents(new LobbyItemListener(teamSelectionGui, game), this);
        pluginManager.registerEvents(new TeamSelectionGuiListener(game, teamSelectionGui, playerTeamHandler), this);
        pluginManager.registerEvents(new PlayerJoinTeamListener(teamSelectionGui), this);
    }

    public static BedWars getInstance() {
        return instance;
    }
}
