package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class ExplosionDeathEffect extends DeathEffect {

    //particle effect
    private static final List<Vector> velocities1 = new ArrayList<>();
    private static final List<Vector> velocities2 = new ArrayList<>();

    static {

        for (int i = 0; i < 15; i++) {
            double pitchDeg = i * 12;


            if (pitchDeg == 0) {
                pitchDeg = 9;
            } else if (pitchDeg == 180) {
                pitchDeg = 171;
            }

            double pitchRad = Math.toRadians(pitchDeg);

            for (int j = 0; j < 30; j++) {
                double yawDeg = j * 12;
                double yawRad = Math.toRadians(yawDeg);

                double x = Math.sin(pitchRad) * Math.cos(yawRad);
                double y = Math.cos(pitchRad);
                double z = Math.sin(pitchRad) * Math.sin(yawRad);

                Vector velocity = new Vector(x, y, z).normalize().multiply(0.4);
                velocities1.add(velocity);
            }
        }

        for (int j = 0; j < 60; j++) {

            double angle = Math.toRadians(j * 6);

            double x = Math.cos(angle);
            double z = Math.sin(angle);
            double y = 0;

            Vector vec = new Vector(x, y, z).normalize().multiply(0.6);
            velocities2.add(vec);
        }
    }

    public ExplosionDeathEffect(@NotNull OfflinePlayer player) {
        super(player);
    }

    @Override
    public void displayDeathEffect() {

        if (!player.isOnline())
            return;

        Player online = (Player) player;

        Location location = online.getLocation();

        location.getWorld().playSound(location,Sound.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.MASTER,0.5F,1F);

        velocities1.stream().forEach(velocity -> {
            location.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, location, 0, velocity.getX(), velocity.getY(), velocity.getZ(), 1);
        });

        velocities2.stream().forEach(velocity -> {
           location.getWorld().spawnParticle(Particle.CLOUD, location, 0, velocity.getX(), velocity.getY(), velocity.getZ(), 1);
        });
    }
}
