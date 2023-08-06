package de.scrupy.bedwars.event;

import de.scrupy.bedwars.GameState;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStateChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final GameState newState;

    public GameStateChangeEvent(GameState newState) {
        this.newState = newState;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public GameState getNewState() {
        return newState;
    }
}
