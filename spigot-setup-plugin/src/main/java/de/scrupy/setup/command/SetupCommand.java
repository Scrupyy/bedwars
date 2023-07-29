package de.scrupy.setup.command;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import de.scrupy.setup.BedWarsSetup;
import de.scrupy.setup.map.MapResultMessage;
import de.scrupy.setup.map.MapSetup;
import de.scrupy.setup.player.PlayerSession;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {
    private final PlayerSession playerSession;

    public SetupCommand(PlayerSession playerSession) {
        this.playerSession = playerSession;
    }

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

        LocalSession localSession = BedWarsSetup.getInstance().getWorldEditPlugin().getSession(player);

        try {
            BlockVector3 positionOne = localSession.getSelection().getMaximumPoint();
            BlockVector3 positionTwo = localSession.getSelection().getMinimumPoint();
            CuboidRegion cuboidRegion = new CuboidRegion(positionOne, positionTwo);

            MapSetup mapSetup = new MapSetup(cuboidRegion, player.getWorld());

            MapResultMessage mapResultMessage = new MapResultMessage(mapSetup.getGameMap(), player);
            mapResultMessage.sendResult();

            playerSession.put(player, mapSetup.getGameMap());
        } catch (IncompleteRegionException exception) {
            player.sendMessage(BedWarsSetup.getPrefix() + "You need to select two locations.");
            return true;
        }
        return true;
    }
}
