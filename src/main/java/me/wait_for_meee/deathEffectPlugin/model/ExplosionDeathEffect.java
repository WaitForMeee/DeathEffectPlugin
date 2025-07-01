package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class ExplosionDeathEffect extends DeathEffect {


    public ExplosionDeathEffect(@NotNull OfflinePlayer player) {
        super(player);
    }

    @Override
    public void displayDeathEffect() {

    }
}
