package de.scrupy.bedwars.map;

import de.scrupy.bedwars.BedWars;
import de.scrupy.common.map.GameMapLocation;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MaterialSpawner {
    private final ItemStack itemStack;
    private final int timeBetweenSpawning;
    private final List<Location> spawnerLocations;

    public MaterialSpawner(ItemStack itemStack, int timeBetweenSpawning, List<GameMapLocation> spawnerLocations) {
        this.itemStack = itemStack;
        this.timeBetweenSpawning = timeBetweenSpawning;
        this.spawnerLocations = gameMapLocationsToLocation(spawnerLocations);
    }

    private List<Location> gameMapLocationsToLocation(List<GameMapLocation> gameMapLocations) {
        List<Location> locations = new ArrayList<>();
        for (GameMapLocation gameMapLocation : gameMapLocations) {
             locations.add(GameMapLocationConverter.toLocation(gameMapLocation).add(0.0, 1.0, 0.0));
        }
        return locations;
    }

    public void startSpawningMaterials() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Location location : spawnerLocations) {
                    World world = location.getWorld();
                    if (world != null)
                        world.dropItem(location, itemStack).setVelocity(new Vector(0, 0.3, 0));
                }
            }
        }.runTaskTimer(BedWars.getInstance(), 0, timeBetweenSpawning);
    }
}
