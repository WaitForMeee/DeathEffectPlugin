package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class RepelDeathEffect extends DeathEffect {

    @Override
    public void displayDeathEffect(@NotNull Location location) {

        Collection<Player> nearbyPlayers = location.getNearbyPlayers(3);

        nearbyPlayers.forEach(player -> {

            Vector vec = location.toVector().subtract(player.getLocation().toVector());

            if (vec.lengthSquared() != 0) {

                Vector velocity = vec.normalize().multiply(-1);

                player.setVelocity(velocity);
            }
        });

        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE,1F,1.5F);
        location.getWorld().playSound(location, Sound.BLOCK_ANVIL_LAND,1F,1.5F);
    }
}
