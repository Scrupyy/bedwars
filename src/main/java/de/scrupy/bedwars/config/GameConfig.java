package de.scrupy.bedwars.config;

import de.scrupy.bedwars.BedWars;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class GameConfig {
    private static final String PREFIX_KEY = "prefix";
    private static final String MESSAGE_CONFIGURATION_SECTION_PATH = "messages";
    private static final String NAME_CONFIGURATION_SECTION_PATH = "names";
    private static GameConfig instance;
    private final Map<String, String> messages;
    private final Map<String, String> names;

    private GameConfig() {
        this.messages = new HashMap<>();
        this.names = new HashMap<>();
        loadMessages();
        loadNames();
    }

    private void loadMessages() {
        ConfigurationSection messageSection = BedWars.getInstance().getConfig().getConfigurationSection(MESSAGE_CONFIGURATION_SECTION_PATH);

        if (messageSection == null)
            return;

        for (String key : messageSection.getKeys(false)) {
            String message = messageSection.getString(key);
            if (message != null) {
                messages.put(key, ChatColor.translateAlternateColorCodes('&', replacePrefixInMessage(message)));
            }
        }
    }

    private void loadNames() {
        ConfigurationSection nameSection = BedWars.getInstance().getConfig().getConfigurationSection(NAME_CONFIGURATION_SECTION_PATH);

        if (nameSection == null)
            return;

        for (String key : nameSection.getKeys(false)) {
            String name = nameSection.getString(key);
            if (name != null) {
                names.put(key, ChatColor.translateAlternateColorCodes('&', name));
            }
        }
    }

    public String getMessage(String key) {
        return messages.getOrDefault(key, "Message not found for key: " + key);
    }

    public String getName(String key) {
        return names.getOrDefault(key, "Name not found for key: " + key);
    }

    public String getMessage(String key, String... replacements) {
        String message = getMessage(key);

        if (replacements != null && replacements.length > 0) {
            for (int i = 0; i < replacements.length; i++) {
                message = message.replace("{" + i + "}", replacements[i]);
            }
        }

        return message;
    }

    private String replacePrefixInMessage(String message) {
        return message.replaceAll("%p", getMessage(PREFIX_KEY));
    }

    public static synchronized GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }
}
