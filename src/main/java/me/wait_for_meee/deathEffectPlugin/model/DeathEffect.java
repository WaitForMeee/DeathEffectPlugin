package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class DeathEffect {

    @NotNull
    protected final Player player;

    public DeathEffect(@NotNull Player player) {
        this.player = player;
    }

    public abstract void displayDeathEffect();

    public static Class<?> getByString(String string) throws ClassNotFoundException {
        return Class.forName("me.wait_for_meee.deathEffectPlugin.model." + string);
    }

    @Override
    public final String toString() {
        return this.getClass().getName();
    }
}
