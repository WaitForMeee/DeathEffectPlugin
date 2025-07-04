package me.wait_for_meee.deathEffectPlugin.listener;

import me.wait_for_meee.deathEffectPlugin.model.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ProjectileListener implements Listener {

    private final static Random random = new Random();

    @EventHandler
    public void onHit(ProjectileHitEvent event) {

        if (event.getEntity() instanceof Snowball) {

            Snowball snowball = (Snowball) event.getEntity();

            if (snowball.getPersistentDataContainer().has(Util.snowballPDC)) {
                coverWithSnow(snowball.getLocation());
            }
        }
    }

    private void coverWithSnow(@NotNull Location origin) {

        List<Location> locations = new ArrayList<>();

        for (int x = -1; x <= 1; ++x) {
            for (int y = -1; y <= 1; ++y) {
                for (int z = -1; z <= 1; ++z) {
                    Location temp = origin.clone().add(x, y, z);

                    Block block = temp.getBlock();
                    Block above = temp.clone().add(0, 1, 0).getBlock();

                    if (!block.getType().isAir() && block.getType().isSolid() && above.getType().equals(Material.AIR)) {
                        locations.add(above.getLocation());
                    }
                }
            }
        }



        for (Location loc : locations) {

            if (random.nextInt(0,10) > 2)
                loc.getBlock().setType(Material.SNOW);
        }

        origin.getWorld().playSound(origin,Sound.BLOCK_SNOW_PLACE,1F,1F);
    }
}
