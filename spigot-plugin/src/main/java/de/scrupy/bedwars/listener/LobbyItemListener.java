package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.lobby.LobbyItem;
import de.scrupy.bedwars.team.TeamSelectionGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyItemListener implements Listener {
    private final TeamSelectionGui teamSelectionGui;
    private final Game game;

    public LobbyItemListener(TeamSelectionGui teamSelectionGui, Game game) {
        this.teamSelectionGui = teamSelectionGui;
        this.game = game;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent interactEvent) {
        if (game.getGameState() != GameState.LOBBY) {
            return;
        }
        interactEvent.setCancelled(true);

        Player player = interactEvent.getPlayer();

        int slot = interactEvent.getPlayer().getInventory().getHeldItemSlot();
        if (slot == LobbyItem.TEAM_SELECTOR.getSlot()) {
            teamSelectionGui.openGui(player);
        }
    }
}
