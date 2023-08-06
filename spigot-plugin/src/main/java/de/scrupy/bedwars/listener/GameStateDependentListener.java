package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GameStateDependentListener implements Listener {
    private final Game game;

    public GameStateDependentListener(Game game) {
        this.game = game;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (game.getGameState() == GameState.LOBBY)
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (game.getGameState() == GameState.LOBBY)
            event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (game.getGameState() == GameState.LOBBY)
            event.setCancelled(true);
    }
}
