package de.scrupy.setup.command;

import de.scrupy.common.map.GameMap;
import de.scrupy.setup.BedWarsSetup;
import de.scrupy.setup.map.MapSaver;
import de.scrupy.setup.player.PlayerSession;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SaveMapCommand implements CommandExecutor {
    private final PlayerSession playerSession;
    private final MapSaver mapSaver;

    public SaveMapCommand(PlayerSession playerSession, MapSaver mapSaver) {
        this.playerSession = playerSession;
        this.mapSaver = mapSaver;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(BedWarsSetup.getPrefix() + "§cCommand only executable for players.");
            return true;
        }

        if (!player.hasPermission("bedwars.setup")) {
            player.sendMessage(BedWarsSetup.getPrefix() + "§cNot enough permissions.");
            return true;
        }

        if (args.length != 3) {
            player.sendMessage(BedWarsSetup.getPrefix()+ "§cUse /saveMap <map-name> <builder-name> <team-players-size>");
            return true;
        }

        GameMap gameMap = playerSession.getGameMap(player);

        if (gameMap != null) {
            try {
                String name = args[0];
                String builderName = args[1];
                int teamPlayers = Integer.parseInt(args[2]);

                gameMap.setName(name);
                gameMap.setBuilderName(builderName);
                gameMap.setTeamPlayers(teamPlayers);

                if (mapSaver.gameMapExists(gameMap)) {
                    player.sendMessage(BedWarsSetup.getPrefix() + "§cthis game map already exists. Change name to save.");
                    return true;
                }

                try {
                    mapSaver.saveGameMapToFile(gameMap);
                    player.sendMessage(BedWarsSetup.getPrefix() + "§asuccessfully §7saved map with file name: §e§l" + mapSaver.getFileName(gameMap));
                } catch (IOException e) {
                    player.sendMessage(BedWarsSetup.getPrefix() + "§cError while saving map file.");
                    e.printStackTrace();
                    return true;
                }
            } catch (NumberFormatException exception) {
                player.sendMessage(BedWarsSetup.getPrefix() + "§cteam-players-size has to be an number.");
                return true;
            }
        } else {
            player.sendMessage(BedWarsSetup.getPrefix() + "§cYou need to setup a map first. Use /setup");
            return true;
        }

        return true;
    }
}
