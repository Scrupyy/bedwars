package de.scrupy.bedwars.util;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerAttributes {
    public static void setAttributes(Player player, boolean shouldSetAsSpectator) {
        if (shouldSetAsSpectator) {
            setDefaultAttributes(player);
            player.setGameMode(GameMode.ADVENTURE);
            return;
        }
        if (Game.getInstance().getGameState() == GameState.LOBBY) {
            setDefaultAttributes(player);
            player.setGameMode(GameMode.ADVENTURE);
        }

        if (Game.getInstance().getGameState() == GameState.INGAME) {
            setDefaultAttributes(player);
            player.setGameMode(GameMode.SURVIVAL);
        }

    }

    private static void setDefaultAttributes(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setLevel(0);
        player.setFlying(false);
        player.setExp(0);
        player.setAllowFlight(false);
    }
}
