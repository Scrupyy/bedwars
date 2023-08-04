package de.scrupy.bedwars.util;

import de.scrupy.bedwars.BedWars;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class Countdown {
    private final int time;
    private int remainingTime;
    private BukkitTask runnable;
    private boolean isRunning;

    public Countdown(int time) {
        this.time = time;
        this.remainingTime = time;
    }

    public void start() {
        onStart();
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                remainingTime--;
                if (remainingTime == 0) {
                    finish();
                } else {
                    onTick();
                }
            }
        }.runTaskTimer(BedWars.getInstance(), 0L, 20L);
        isRunning = true;
    }

    public void stop() {
        if (runnable != null) {
            runnable.cancel();
            onStop();
            isRunning = false;
        }
    }

    public void finish() {
        onFinish();
        stop();
    }

    public void resetTime() {
        this.remainingTime = time;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public abstract void onStart();
    public abstract void onStop();
    public abstract void onTick();
    public abstract void onFinish();
}
