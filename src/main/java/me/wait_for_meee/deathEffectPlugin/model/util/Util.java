package me.wait_for_meee.deathEffectPlugin.model.util;

import me.wait_for_meee.deathEffectPlugin.DeathEffectPlugin;
import org.bukkit.NamespacedKey;

public final class Util {

    public static final NamespacedKey snowballPDC;
    public static final NamespacedKey pigPDC;
    public static final DeathEffectPlugin instance = DeathEffectPlugin.getPlugin(DeathEffectPlugin.class);

    static {
        snowballPDC = new NamespacedKey(DeathEffectPlugin.getPlugin(DeathEffectPlugin.class),"snowball");
        pigPDC =  new NamespacedKey(DeathEffectPlugin.getPlugin(DeathEffectPlugin.class),"pig");
    }
}
