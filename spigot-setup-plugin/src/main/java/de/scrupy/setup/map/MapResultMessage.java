package de.scrupy.setup.map;

import de.scrupy.common.map.GameMapLocation;
import de.scrupy.common.map.GameMap;
import org.bukkit.entity.Player;

public class MapResultMessage {
    private final GameMap gameMap;
    private final Player player;

    public MapResultMessage(GameMap gameMap, Player player) {
        this.gameMap = gameMap;
        this.player = player;
    }

    public void sendResult() {
        sendTeamSpawnsInfo();
        sendSpawnerInfo();
    }

    private void sendSpawnerInfo() {
        player.sendMessage("§7---- §6§lRegistered spawners §7----");
        player.sendMessage(" ");
        player.sendMessage("§6§lGold §7Spawner: §e§l" + gameMap.getGoldSpawner().size());
        player.sendMessage("§f§lIron §7Spawner: §e§l" + gameMap.getIronSpawner().size());
        player.sendMessage("§c§lBronze §7Spawner: §e§l" + gameMap.getBronzeSpawner().size());
        player.sendMessage(" ");
        player.sendMessage("§7--------------------------------");
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
