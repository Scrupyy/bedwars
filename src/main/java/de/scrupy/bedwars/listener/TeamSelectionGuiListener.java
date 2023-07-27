package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.Team;
import de.scrupy.bedwars.team.TeamSelectionGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TeamSelectionGuiListener implements Listener {
    private final TeamSelectionGui teamSelectionGui;
    private final PlayerTeamHandler playerTeamHandler;

    public TeamSelectionGuiListener(TeamSelectionGui teamSelectionGui, PlayerTeamHandler playerTeamHandler) {
        this.teamSelectionGui = teamSelectionGui;
        this.playerTeamHandler = new PlayerTeamHandler();
    }

    @EventHandler
    public void onClick(InventoryClickEvent clickEvent) {
        if (clickEvent.getView().getTitle().equals(GameConfig.getInstance().getName(TeamSelectionGui.TEAM_SELECTION_TITLE_KEY))) {
            clickEvent.setCancelled(true);
            ItemStack itemStack = clickEvent.getCurrentItem();

            if (itemStack == null)
                return;

            Player player = (Player) clickEvent.getWhoClicked();
            Team team = teamSelectionGui.getTeamBySlot(clickEvent.getSlot());

            if (team != null) {
                if (team.getPlayers().contains(player)) {
                    player.sendMessage(GameConfig.getInstance().getMessage("alreadyJoinedTeamMessage"));
                } else {
                    playerTeamHandler.addPlayerToTeam(team, player);
                    player.sendMessage(GameConfig.getInstance().getMessage("joinTeamMessage", team.getColoredName()));
                }
            }
        }
    }
}
