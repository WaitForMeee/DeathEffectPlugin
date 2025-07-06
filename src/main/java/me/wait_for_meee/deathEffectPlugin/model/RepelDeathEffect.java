package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class RepelDeathEffect extends DeathEffect {

    public RepelDeathEffect(@NotNull OfflinePlayer player) {
        super(player);
    }

    @Override
    public void displayDeathEffect() {

        if (!player.isOnline())
            return;

        Player p = (Player) player;

        Location location = p.getLocation();

        Collection<Player> nearbyPlayers = location.getNearbyPlayers(3);
        nearbyPlayers.remove(p);

        nearbyPlayers.forEach(player -> {

            Vector velocity = location.toVector().subtract(player.getLocation().toVector()).normalize().multiply(-1);

            player.setVelocity(velocity);
        });

        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE,1F,1.5F);
        location.getWorld().playSound(location, Sound.BLOCK_ANVIL_LAND,1F,1.5F);
    }
}
