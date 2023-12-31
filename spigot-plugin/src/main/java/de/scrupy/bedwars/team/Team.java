package de.scrupy.bedwars.team;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private final String name;
    private final ChatColor color;
    private final Material coloredWool;
    private final List<Player> players;
    private int maxPlayers;
    private boolean bedDestroyed;

    public Team(String name, ChatColor color, Material coloredWool) {
        this.name = name;
        this.color = color;
        this.coloredWool = coloredWool;
        this.players = new ArrayList<>();
        this.bedDestroyed = false;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public Material getColoredWool() {
        return coloredWool;
    }

    public String getColoredName() {
        return color + name;
    }

    protected void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setBedDestroyed(boolean bedDestroyed) {
        this.bedDestroyed = bedDestroyed;
    }

    public boolean isBedDestroyed() {
        return bedDestroyed;
    }

    protected void addPlayer(Player player) {
        if (player != null && !players.contains(player))
            players.add(player);
    }

    protected void removePlayer(Player player) {
        if (player != null)
            players.remove(player);
    }

    public boolean contains(Player player) {
        return players.contains(player);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
