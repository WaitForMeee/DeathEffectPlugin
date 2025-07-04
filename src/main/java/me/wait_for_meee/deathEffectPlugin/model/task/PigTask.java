package me.wait_for_meee.deathEffectPlugin.model.task;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.jetbrains.annotations.NotNull;

public final class PigTask implements Runnable{

    private static PigTaskScheduler pigTaskScheduler;

    public static void setPigTaskScheduler(@NotNull PigTaskScheduler scheduler) {
        pigTaskScheduler = scheduler;
    }

    public static PigTaskScheduler getPigTaskScheduler() {
        return pigTaskScheduler;
    }

    /// TODO make a ring of redstone dust particles (orange red and yellow)

    private Pig pig;

    @NotNull
    private final Location location;

    private int counter = 3*20;
    private boolean isPigSpawned = false;

    public PigTask(@NotNull Location location) {
        this.location = location;
    }


    @Override
    public void run() {

        if (!isPigSpawned) {
            pig = (Pig) location.getWorld().spawnEntity(location, EntityType.PIG);
            pig.setAI(false);
            pig.setInvulnerable(true);
            isPigSpawned = true;
        }

        if (pig != null) {
            pig.teleport(pig.getLocation().add(0, 0.05, 0));
            pig.setBodyYaw(pig.getYaw() + 0.05F);
        }
        --counter;

        if (counter <= 0) {

            Location effectLocation = this.location.add(0,0.05*60,0);

            if (pig != null) {
                effectLocation = pig.getLocation();
                pig.remove();
            }

            fireworkEffect(effectLocation);

            pigTaskScheduler.update(this);
        }
    }

    /// TODO implement this logic
    private void fireworkEffect(Location location) {

    }
}
