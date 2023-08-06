package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockChangeListener implements Listener {
    private final Game game;
    private final List<Location> blockLocations;

    public BlockChangeListener(Game game) {
        this.game = game;
        this.blockLocations = new ArrayList<>();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (game.getGameState() != GameState.INGAME) {
            event.setCancelled(true);
        }
        Location blockLocation = event.getBlock().getLocation();
        blockLocations.add(blockLocation);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Location blockLocation = event.getBlock().getLocation();
        if (event.getBlock().getType().name().endsWith("BED")) {
            return;
        }
        if (blockLocations.contains(blockLocation)) {
            blockLocations.remove(blockLocation);
        } else {
            event.setCancelled(true);
        }
    }
}
