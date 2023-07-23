package de.scrupy.bedwars.player;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.lobby.LobbyItem;
import de.scrupy.bedwars.util.PlayerAttributes;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class PlayerHandler {
    private final GamePlayerRepository gamePlayerRepository;

    public PlayerHandler() {
        gamePlayerRepository = new GamePlayerRepository();
    }

    public void handleJoin(Player player) {
        setupPlayer(player);
        gamePlayerRepository.addPlayer(player);
    }

    public void handleQuit(Player player) {
        gamePlayerRepository.removePlayer(player);
    }

    private void setupPlayer(Player player) {
        if (Game.getInstance().getGameState() == GameState.LOBBY) {
            PlayerAttributes.setAttributes(player, false);
            LobbyItem.setLobbyItems(player);
        } else {
            PlayerAttributes.setAttributes(player, true);
        }
    }

    @Nullable
    public GamePlayer getGamePlayer(Player player) {
        return gamePlayerRepository.getGamePlayer(player);
    }
}
