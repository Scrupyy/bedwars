package de.scrupy.bedwars.lobby;

import de.scrupy.bedwars.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum LobbyItem {
    TEAM_SELECTOR(0, new ItemBuilder(Material.RED_BED).setDisplayName("§6Team Selector §7<Right-Click>").build());

    private final int slot;
    private final ItemStack itemStack;

    LobbyItem(int slot, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public static void setLobbyItems(Player player) {
        for (LobbyItem lobbyItem : LobbyItem.values()) {
            player.getInventory().setItem(lobbyItem.getSlot(), lobbyItem.getItemStack());
        }
    }
}
