package me.wait_for_meee.deathEffectPlugin.model.task;

import me.wait_for_meee.deathEffectPlugin.model.task.scheduler.SkullTaskScheduler;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class SkullTask implements Runnable{

    @NotNull
    private static SkullTaskScheduler skullTaskScheduler;

    private static final List<Vector> vecList = new ArrayList<>();
    private static final List<Vector> ring = new ArrayList<>();

    private static final Particle.DustOptions blackDust = new Particle.DustOptions(Color.fromRGB(0,0,0),1F);

    static {
        for (int j = 0; j < 360; j++) {

            double rad = Math.toRadians(j);

            double x = Math.cos(rad);
            double z = Math.sin(rad);
            double y = 0;

            Vector vec = new Vector(x, y, z).normalize().multiply(0.6F);
            vecList.add(vec);
        }

        for (int j = 0; j < 360; j += 45) {

            double rad = Math.toRadians(j);

            double x = Math.cos(rad);
            double z = Math.sin(rad);
            double y = 0;

            Vector vec = new Vector(x, y, z).normalize().multiply(0.5F);
            ring.add(vec);
        }
    }

    public static void setSkullTaskScheduler(@NotNull SkullTaskScheduler scheduler) {
        skullTaskScheduler = scheduler;
    }

    public static @NotNull SkullTaskScheduler getSkullTaskScheduler() {
        return skullTaskScheduler;
    }


    @NotNull
    private final Location location;
    private ArmorStand skull;

    private boolean isSkullSpawned = false;
    private int counter = 4*20;

    public SkullTask(@NotNull Location location) {
        this.location = location;
    }

    @Override
    public void run() {

        if (!isSkullSpawned) {

            skull = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

            skull.setInvulnerable(true);
            skull.setGravity(false);
            skull.setMarker(true);
            skull.setAI(false);
            skull.setInvisible(true);

            skull.setHelmet(new ItemStack(Material.WITHER_SKELETON_SKULL));

            isSkullSpawned = true;
        }

        if (skull != null) {

            Location loc = skull.getLocation();

            skull.teleport(loc.clone().add(0,0.05,0));

            loc.add(0,1.25,0);

            World world = location.getWorld();

            for (Vector vec : ring) {

                Location loc1 = loc.clone().add(vec);

                world.spawnParticle(Particle.REDSTONE,loc1,0,0.0,0.0,0.0,1,blackDust);
            }

            world.spawnParticle(Particle.END_ROD,loc,0,0,0,0,0);
        }

        if (counter <= 0) {

            Location lightning_loc = location.add(0,0.05*4*20,0).add(0,1.25,0);

            if (skull != null) {
                lightning_loc = skull.getLocation();
                skull.remove();
            }

            lightning_loc.getWorld().strikeLightningEffect(lightning_loc);

            for (Vector vec : vecList) {

                lightning_loc.getWorld().spawnParticle(Particle.CLOUD,lightning_loc,0,vec.getX(),vec.getY(),vec.getZ(),1);
            }

            lightning_loc.getWorld().playSound(lightning_loc, Sound.BLOCK_BEACON_DEACTIVATE,1F,1F);

            skullTaskScheduler.update(this);
        }

        --counter;
    }
}
