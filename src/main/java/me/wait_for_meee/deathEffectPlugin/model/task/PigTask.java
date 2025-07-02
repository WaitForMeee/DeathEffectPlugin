package me.wait_for_meee.deathEffectPlugin.model.task;

import org.bukkit.Location;
import org.bukkit.entity.Pig;
import org.jetbrains.annotations.NotNull;

public class PigTask implements Runnable{

    private static PigTaskScheduler pigTaskScheduler;

    public static void setPigTaskScheduler(@NotNull PigTaskScheduler scheduler) {
        pigTaskScheduler = scheduler;
    }


    private Pig pig;
    private final Location location;
    private int counter = 3*20;
    private boolean isPigSpawned = false;

    public PigTask(Location location) {
        this.location = location;
    }


    @Override
    public void run() {

    }
}
