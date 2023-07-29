package de.scrupy.setup.map;

import de.scrupy.common.map.GameMapLocation;
import de.scrupy.common.map.Map;
import org.bukkit.entity.Player;

public class MapResultMessage {
    private final Map gameMap;
    private final Player player;

    public MapResultMessage(Map gameMap, Player player) {
        this.gameMap = gameMap;
        this.player = player;
    }

    public void sendResult() {
        sendTeamSpawnsInfo();
    }

    private void sendTeamSpawnsInfo() {
        player.sendMessage("§7---- §6§lRegistered team spawns §7----");
        player.sendMessage(" ");
        gameMap.getTeamSpawns().forEach((teamName, gameMapLocation) -> {
            player.sendMessage("§3" + teamName + " " + gameMapLocationToString(gameMapLocation));
        });
        player.sendMessage(" ");
        player.sendMessage("§7--------------------------------");
    }

    private String gameMapLocationToString(GameMapLocation loc) {
        // [x, y, z]
        return "§8§l[§a" + loc.getX() + "§7, §b" + loc.getY() + "§7, §e" + loc.getZ() + "§8§l]";
    }
}
