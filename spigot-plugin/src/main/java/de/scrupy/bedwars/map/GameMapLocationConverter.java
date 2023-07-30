package de.scrupy.bedwars.map;

import de.scrupy.common.map.GameMapLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class GameMapLocationConverter {
    public static Location toLocation(GameMapLocation gameMapLocation) {
        World world = Bukkit.getWorld(gameMapLocation.worldName());

        if (world == null) {
            Bukkit.getLogger().warning("Error while serializing location. World with name "
                    + gameMapLocation.worldName() + " not found.");
            world = Bukkit.getWorld("world");
        }

        return new Location(
                world,
                gameMapLocation.getX(),
                gameMapLocation.getY(),
                gameMapLocation.getZ(),
                gameMapLocation.getYaw(),
                gameMapLocation.getPitch()
        );
    }
}
