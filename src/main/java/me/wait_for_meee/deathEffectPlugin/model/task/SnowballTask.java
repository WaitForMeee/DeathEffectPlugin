package me.wait_for_meee.deathEffectPlugin.model.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
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

    private int counter = 5*20;
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

            isSnowballSpawned = true;
        }

        if (snowball != null && snowball.isDead()) {

            coverWithSnow(snowball.getLocation());

            snowball.remove();

            snowballTaskScheduler.update(this);

        } else if (counter <= 0) {

            if (snowball != null) {
                coverWithSnow(snowball.getLocation());
                snowball.remove();
            }


            snowballTaskScheduler.update(this);
        }

        --counter;
    }

    private @NotNull Vector randomVec() {

        double x = random.nextDouble(-1,1);
        double y = random.nextDouble(-1,1);
        double z = random.nextDouble(-1,1);

        return new Vector(x,y,z);
    }

    private void coverWithSnow(@NotNull Location location) {

        List<Location> locations = new ArrayList<>();

        for (int i = -1; i <= 1;++i) {
            for (int j = -1; j <= 1;++j) {
                for (int k = -1; k<=1;++k) {

                    Location temp = location.add(i,k,j);

                    if (!temp.getBlock().getType().equals(Material.AIR) &&
                            temp.add(0,1,0).getBlock().getType().equals(Material.AIR))
                    {
                        locations.add(temp);
                    }
                }
            }
        }

        locations.stream().forEach(loc -> {
            location.getBlock().setBlockData(Bukkit.createBlockData(Material.SNOW));
        });

        location.getWorld().playSound(location, Sound.BLOCK_SNOW_BREAK,1,1);

    }
}
