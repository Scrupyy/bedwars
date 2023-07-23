package de.scrupy.bedwars.team;

import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamSelectionGui {
    public static final String TEAM_SELECTION_TITLE_KEY = "teamSelectionGuiTitle";
    private final Inventory inventory;
    private final TeamManager teamManager;
    private final Map<Integer, Team> teamSlot;

    public TeamSelectionGui(TeamManager teamManager) {
        this.teamManager = teamManager;
        this.teamSlot = new HashMap<>();
        this.inventory = Bukkit.createInventory(null, 9, GameConfig.getInstance().getName(TEAM_SELECTION_TITLE_KEY));
        setupInventory();
    }

    private void setupInventory() {
        int i = 0;
        for (Team team : teamManager.getAvailableTeams()) {
            ItemStack teamSelectionItem = new ItemBuilder(team.getColoredWool())
                            .setDisplayName(getTeamSelectionItemDisplayName(team))
                            .setLore(getTeamSelectionItemDescription(team))
                            .build();
            inventory.setItem(i, teamSelectionItem);
            teamSlot.put(i, team);
            i++;
        }
    }

    private String getTeamSelectionItemDisplayName(Team team) {
        return team.getColor() + team.getName() + " §7(" + team.getPlayers().size() + "/" + team.getMaxPlayers() + ")";
    }

    private List<String> getTeamSelectionItemDescription(Team team) {
        List<String> description = new ArrayList<>();
        String descriptionFormat = GameConfig.getInstance().getName("teamSelectionItemFormat");

        description.add(" ");

        for (int i = 0; i < team.getMaxPlayers(); i++) {
            if (team.getPlayers().size() > i) {
                Player player = team.getPlayers().get(i);
                if (player != null) {
                    description.add(String.format(descriptionFormat, team.getColor() + player.getName()));
                    continue;
                }
            }
            description.add(String.format(descriptionFormat, " "));
        }
        return description;
    }

    @Nullable
    public Team getTeamBySlot(int slot) {
        return teamSlot.get(slot);
    }

    public void updateGui() {
       teamSlot.forEach(this::updateItemOnSlot);
    }

    private void updateItemOnSlot(int slot, Team team) {
        ItemStack itemStack = inventory.getItem(slot);

        if (itemStack == null)
            return;

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null)
            return;

        itemMeta.setDisplayName(getTeamSelectionItemDisplayName(team));
        itemMeta.setLore(getTeamSelectionItemDescription(team));
        itemStack.setItemMeta(itemMeta);
    }

    public void openGui(Player player) {
        player.openInventory(inventory);
    }
}
