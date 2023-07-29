package de.scrupy.bedwars.event;

import de.scrupy.bedwars.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJoinTeamEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Team team;

    public PlayerJoinTeamEvent(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }
}
