package de.scrupy.setup;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import de.scrupy.setup.command.SaveMapCommand;
import de.scrupy.setup.command.SetupCommand;
import de.scrupy.setup.map.MapSaver;
import de.scrupy.setup.player.PlayerSession;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWarsSetup extends JavaPlugin {
    private static BedWarsSetup instance;
    private static final String PREFIX = "§5§lBedWars-Setup §7> ";

    private WorldEditPlugin worldEditPlugin;

    @Override
    public void onEnable() {
        instance = this;
        this.worldEditPlugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

        PlayerSession playerSession = new PlayerSession();
        MapSaver mapSaver = new MapSaver();

        getCommand("setup").setExecutor(new SetupCommand(playerSession));
        getCommand("saveMap").setExecutor(new SaveMapCommand(playerSession, mapSaver));

        Bukkit.getConsoleSender().sendMessage(PREFIX + "§7successfully §a§lstarted.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§7successfully §c§lstopped.");
    }

    public WorldEditPlugin getWorldEditPlugin() {
        return worldEditPlugin;
    }

    public static String getPrefix() {
        return PREFIX;
    }

    public static BedWarsSetup getInstance() {
        return instance;
    }
}
