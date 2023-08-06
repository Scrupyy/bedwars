package de.scrupy.bedwars.listener;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.GameState;
import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.scoreboard.IngameScoreboard;
import de.scrupy.bedwars.team.PlayerTeamHandler;
import de.scrupy.bedwars.team.Team;
import de.scrupy.bedwars.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import javax.annotation.Nullable;

public class BedBreakListener implements Listener {
    private final Game game;
    private final TeamManager teamManager;
    private final PlayerTeamHandler playerTeamHandler;

    public BedBreakListener(Game game, TeamManager teamManager, PlayerTeamHandler playerTeamHandler) {
        this.game = game;
        this.teamManager = teamManager;
        this.playerTeamHandler = playerTeamHandler;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (game.getGameState() != GameState.INGAME) {
            return;
        }

        if (!event.getBlock().getType().name().endsWith("BED")) {
            return;
        }

        Block blockBelow = event.getBlock().getLocation().subtract(0.0, 1.0, 0.0).getBlock();
        if (blockBelow.getType() == Material.AIR || !blockBelow.getType().name().endsWith("WOOL")) {
            return;
        }

        Team team = getTeamByWoolBlock(blockBelow);

        if (team == null) {
            return;
        }

        Player player = event.getPlayer();
        Team playerTeam = playerTeamHandler.getPlayerTeam(player);

        if (playerTeam == team) {
            player.sendMessage(GameConfig.getInstance().getMessage("selfDestructBedAttemptMessage"));
            event.setCancelled(true);
            return;
        }

        event.setDropItems(false);
        team.setBedDestroyed(true);
        IngameScoreboard.updateTeamSuffix(team);
        Bukkit.broadcastMessage(GameConfig.getInstance().getMessage("bedDestroyedMessage", team.getColoredName()));
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1L, 1L);
        });
    }

    @Nullable
    private Team getTeamByWoolBlock(Block woolBlock) {
        Team toReturn = null;
        for (Team team : teamManager.getAvailableTeams()) {
            if (team.getColoredWool().equals(woolBlock.getType())) {
                toReturn = team;
                break;
            }
        }
        return toReturn;
    }
}
