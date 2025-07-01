package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DeathEffect {

    @NotNull
    protected final OfflinePlayer player;

    public DeathEffect(@NotNull OfflinePlayer player) {
        this.player = player;
    }

    public abstract void displayDeathEffect();

    @SuppressWarnings("unchecked")
    @Nullable
    public static Class<? extends DeathEffect> getByString(String string) {
        try {
            return (Class<? extends DeathEffect>) Class.forName("me.wait_for_meee.deathEffectPlugin.model." + string);
        } catch (final Exception e) {
            return null;
        }
    }

    @Override
    public final String toString() {
        return this.getClass().getName();
    }
}
