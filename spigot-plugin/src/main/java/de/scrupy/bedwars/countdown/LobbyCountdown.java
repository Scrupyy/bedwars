package de.scrupy.bedwars.countdown;

import de.scrupy.bedwars.config.GameConfig;
import de.scrupy.bedwars.map.MapTeleport;
import de.scrupy.bedwars.util.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.annotation.Nullable;

public class LobbyCountdown extends Countdown {
    private final MapTeleport mapTeleport;

    public LobbyCountdown(int time, MapTeleport mapTeleport) {
        super(time);
        this.mapTeleport = mapTeleport;
    }

    @Override
    public void onStart() {
        broadcastRemainingTime(null);
    }

    @Override
    public void onStop() { }

    @Override
    public void onTick() {
        if (getRemainingTime() % 10 == 0 || getRemainingTime() == 5)
            broadcastRemainingTime(null);

        if (getRemainingTime() == 3)
            broadcastRemainingTime(ChatColor.GREEN);

        if (getRemainingTime() == 2)
            broadcastRemainingTime(ChatColor.YELLOW);

        if (getRemainingTime() == 1)
            broadcastRemainingTime(ChatColor.RED);
    }

    @Override
    public void onFinish() {
        mapTeleport.teleportAllTeams();
    }

    private void broadcastRemainingTime(@Nullable ChatColor timeColor) {
        String coloredRemainingTime = String.valueOf(getRemainingTime());
        if (timeColor != null) {
            coloredRemainingTime = timeColor + "" + getRemainingTime();
        }
        Bukkit.broadcastMessage(GameConfig.getInstance().getMessage("lobbyCountdownRemainingTime", coloredRemainingTime));
    }
}
