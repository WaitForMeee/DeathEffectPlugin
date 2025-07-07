package me.wait_for_meee.deathEffectPlugin.model;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DeathEffect {

    public DeathEffect() {
    }

    public abstract void displayDeathEffect(@NotNull Location location);

    @SuppressWarnings("unchecked")
    @Nullable
    public static Class<? extends DeathEffect> getByString(@NotNull String string) {
        try {
            return (Class<? extends DeathEffect>) Class.forName("me.wait_for_meee.deathEffectPlugin.model." + string);
        } catch (final Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static Class<? extends DeathEffect> getByShortName(@NotNull String string) {
        try {
            return (Class<? extends DeathEffect>) Class.forName("me.wait_for_meee.deathEffectPlugin.model." + string + "DeathEffect");
        } catch (final Exception e) {
            return null;
        }
    }



    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }


    public final String toShortName() {
        return this.toString().replace("DeathEffect", "");
    }
}
