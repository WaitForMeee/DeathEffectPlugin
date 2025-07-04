package me.wait_for_meee.deathEffectPlugin.model.task;

import me.wait_for_meee.deathEffectPlugin.model.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowballTask implements Runnable {

    private static final Random random = new Random();
    private static SnowballTaskScheduler snowballTaskScheduler;


    private Snowball snowball;

    @NotNull
    private final Location location;

    private int counter = 10*20;
    private boolean isSnowballSpawned = false;

    public SnowballTask(@NotNull Location location) {
        this.location = location;
    }

    public static void setSnowballScheduler(@NotNull SnowballTaskScheduler scheduler) {
        snowballTaskScheduler = scheduler;
    }

    public static SnowballTaskScheduler getSnowballTaskScheduler() {
        return snowballTaskScheduler;
    }

    @Override
    public void run() {

        if (!isSnowballSpawned) {

            snowball = (Snowball) location.getWorld().spawnEntity(location, EntityType.SNOWBALL);

            snowball.setVelocity(randomVec());

            snowball.getPersistentDataContainer().set(Util.snowballPDC, PersistentDataType.BOOLEAN,true);

            isSnowballSpawned = true;
        }

        if (snowball != null && snowball.isDead()) {

            snowball.remove();

            snowballTaskScheduler.update(this);

        } else if (counter <= 0) {

            if (snowball != null) {
                snowball.remove();
            }


            snowballTaskScheduler.update(this);
        }

        --counter;
    }

    private @NotNull Vector randomVec() {

        double x = random.nextDouble(-0.3,0.3);
        double y = random.nextDouble(0.3,0.5);
        double z = random.nextDouble(-0.3,0.3);

        return new Vector(x,y,z);
    }
}
