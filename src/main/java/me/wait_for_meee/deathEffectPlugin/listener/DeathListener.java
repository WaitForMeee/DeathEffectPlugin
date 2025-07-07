package me.wait_for_meee.deathEffectPlugin.listener;

import me.wait_for_meee.deathEffectPlugin.core.EffectManager;
import me.wait_for_meee.deathEffectPlugin.model.DeathEffect;
import me.wait_for_meee.deathEffectPlugin.model.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public final class DeathListener implements Listener {

    @NotNull
    private final EffectManager effectManager;

    public DeathListener(@NotNull EffectManager effectManager) {
        this.effectManager = effectManager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player victim = event.getPlayer();

        Player killer = victim.getKiller();

        if (killer != null) {

            DeathEffect deathEffect = effectManager.getEffect(killer.getUniqueId());

            if (deathEffect != null) {

                deathEffect.displayDeathEffect(victim.getLocation());
            }
        }
    }

}
