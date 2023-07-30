package de.scrupy.bedwars.config;

import de.scrupy.bedwars.BedWars;
import org.bukkit.configuration.ConfigurationSection;

public class GameSettingsConfig {
    private static GameSettingsConfig instance;
    private final ConfigurationSection configurationSection;

    public GameSettingsConfig() {
        this.configurationSection = BedWars.getInstance().getConfig().getConfigurationSection("settings");
    }

    public int getInteger(String key) {
        return configurationSection.getInt(key);
    }

    public String getString(String key) {
        return configurationSection.getString(key);
    }

    public static GameSettingsConfig getInstance() {
        if (instance == null) {
            instance = new GameSettingsConfig();
        }
        return instance;
    }
}
