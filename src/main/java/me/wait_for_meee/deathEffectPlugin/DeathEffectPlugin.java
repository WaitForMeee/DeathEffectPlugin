package me.wait_for_meee.deathEffectPlugin;

import me.wait_for_meee.deathEffectPlugin.core.EffectManager;
import me.wait_for_meee.deathEffectPlugin.listener.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathEffectPlugin extends JavaPlugin {

    private EffectManager effectManager;

    @Override
    public void onEnable() {

        effectManager = new EffectManager(this);

        getServer().getPluginManager().registerEvents(new DeathListener(effectManager),this);

    }

    @Override
    public void onDisable() {

    }
}
