package me.wait_for_meee.deathEffectPlugin.model.task;

import me.wait_for_meee.deathEffectPlugin.model.task.scheduler.PigTaskScheduler;
import me.wait_for_meee.deathEffectPlugin.model.util.Util;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class PigTask implements Runnable{

    @NotNull
    private static PigTaskScheduler pigTaskScheduler;

    private static final Particle.DustOptions redDust = new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1.5F);
    private static final Particle.DustOptions orangeDust = new Particle.DustOptions(Color.fromRGB(255, 165, 0), 1.5F);
    private static final Particle.DustOptions yellowDust = new Particle.DustOptions(Color.fromRGB(255, 255, 0), 1.5F);
    private static final List<Vector> vecList;

    static {

        vecList = new ArrayList<>();

        for (int j = 0; j < 15; j++) {

            double angle = Math.toRadians(j * 24);

            double x = Math.cos(angle);
            double z = Math.sin(angle);
            double y = 0;

            Vector vec = new Vector(x, y, z).normalize().multiply(1F);
            vecList.add(vec);
        }
    }

    public static void setPigTaskScheduler(@NotNull PigTaskScheduler scheduler) {
        pigTaskScheduler = scheduler;
    }

    public static @NotNull PigTaskScheduler getPigTaskScheduler() {
        return pigTaskScheduler;
    }

    private Pig pig;

    @NotNull
    private final Location location;

    private int counter = 4*20;
    private boolean isPigSpawned = false;

    public PigTask(@NotNull Location location) {
        this.location = location;
    }


    @Override
    public void run() {

        if (!isPigSpawned) {

            pig = (Pig) location.getWorld().spawnEntity(location, EntityType.PIG);

            pig.setAI(false);
            pig.setSaddle(true);
            pig.getPersistentDataContainer().set(Util.pigPDC, PersistentDataType.BOOLEAN,true);

            pig.setInvulnerable(true);

            isPigSpawned = true;
        }

        if (pig != null) {

            pig.teleport(pig.getLocation().add(0, 0.05, 0));
            particleRing(pig.getLocation());
        }
        --counter;

        if (counter <= 0) {

            Location effectLocation = this.location.add(0,0.05*4*20,0);

            if (pig != null) {
                effectLocation = pig.getLocation();
                pig.remove();
            }

            fireworkEffect(effectLocation);

            pigTaskScheduler.update(this);
        }
    }

    private void fireworkEffect(@NotNull Location location) {

        location.getWorld().spawnParticle(
                Particle.FIREWORKS_SPARK, // Type
                location,                 // Center
                50,                       // Count
                0.5, 0.5, 0.5,            // Offset (X, Y, Z)
                0.01                     // Speed
        );

        location.getWorld().playSound(location, Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 2F, 1F);
        location.getWorld().playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST_FAR, 2F, 1F);
        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 2F, 1.5F);
    }

    private void particleRing(@NotNull Location location) {

        int buff = 0;

        location.add(0,0.3,0);

        for (Vector vec : vecList) {

            Location loc = location.clone().add(vec);

            if (buff % 3 == 0) {
                loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0, 0, 0, 0, redDust);
            } else if (buff % 3 == 1) {
                loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0, 0, 0, 0, orangeDust);
            } else if (buff % 3 == 2) {
                loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, 0, 0, 0, 0, yellowDust);
            }
            ++buff;

        }
    }
}
