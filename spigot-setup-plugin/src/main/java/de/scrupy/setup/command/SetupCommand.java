package de.scrupy.setup.command;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import de.scrupy.setup.BedWarsSetup;
import de.scrupy.setup.map.MapResultMessage;
import de.scrupy.setup.map.MapSetup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(BedWarsSetup.getPrefix() + "§cCommand only executable for players.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("bedwars.setup")) {
            player.sendMessage(BedWarsSetup.getPrefix() + "§cNot enough permissions.");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage(BedWarsSetup.getPrefix() + "§cUse /setup <map-name> <builder-name>");
            return true;
        }

        LocalSession localSession = BedWarsSetup.getInstance().getWorldEditPlugin().getSession(player);

        try {

            BlockVector3 positionOne = localSession.getSelection().getMaximumPoint();
            BlockVector3 positionTwo = localSession.getSelection().getMinimumPoint();
            CuboidRegion cuboidRegion = new CuboidRegion(positionOne, positionTwo);

            String mapName = args[0];
            String builderName = args[1];
            MapSetup mapSetup = new MapSetup(mapName, builderName, cuboidRegion, player.getWorld());

            MapResultMessage mapResultMessage = new MapResultMessage(mapSetup.getGameMap(), player);
            mapResultMessage.sendResult();

        } catch (IncompleteRegionException e) {
            player.sendMessage(BedWarsSetup.getPrefix() + "You need to select two locations.");
        }

        return true;
    }
}
