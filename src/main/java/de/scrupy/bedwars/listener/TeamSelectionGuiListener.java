package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.team.Team;
import de.scrupy.bedwars.team.TeamManager;
import de.scrupy.bedwars.team.TeamSelectionGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TeamSelectionGuiListener implements Listener {
    private final TeamManager teamManager;
    private final TeamSelectionGui teamSelectionGui;

    public TeamSelectionGuiListener(TeamManager teamManager, TeamSelectionGui teamSelectionGui) {
        this.teamManager = teamManager;
        this.teamSelectionGui = teamSelectionGui;
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
                    team.addPlayer((Player) clickEvent.getWhoClicked());
                    player.sendMessage(GameConfig.getInstance().getMessage("joinTeamMessage", team.getColoredName()));
                    teamSelectionGui.updateGui();
                }
            }
        }
    }
}
