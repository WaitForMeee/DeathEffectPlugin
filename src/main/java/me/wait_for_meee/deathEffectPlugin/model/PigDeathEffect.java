package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

//a pig flies up after the player's death and then explodes with firework burst
public final class PigDeathEffect extends DeathEffect {


    public PigDeathEffect(@NotNull OfflinePlayer player) {
        super(player);
    }

    @Override
    public void displayDeathEffect() {

    }
}
