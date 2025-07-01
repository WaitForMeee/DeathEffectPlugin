package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;

//when a player dies, snowballs are thrown in random directions which cover the ground with snow when they hit it
public final class SnowballDeathEffect extends DeathEffect {

    public SnowballDeathEffect(Player player) {
        super(player);
    }

    @Override
    public void displayDeathEffect() {

    }
}
