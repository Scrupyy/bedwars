package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
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
    private final Game game;
    private final TeamSelectionGui teamSelectionGui;
    private final PlayerTeamHandler playerTeamHandler;

    public TeamSelectionGuiListener(Game game, TeamSelectionGui teamSelectionGui, PlayerTeamHandler playerTeamHandler) {
        this.game = game;
        this.teamSelectionGui = teamSelectionGui;
        this.playerTeamHandler = playerTeamHandler;
    }

    @EventHandler
    public void onClick(InventoryClickEvent clickEvent) {
        if (clickEvent.getView().getTitle().equals(GameConfig.getInstance().getName(TeamSelectionGui.TEAM_SELECTION_TITLE_KEY))) {
            clickEvent.setCancelled(true);
            ItemStack itemStack = clickEvent.getCurrentItem();

            if (itemStack == null)
                return;

            if (game.getGameState() != GameState.LOBBY)
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
