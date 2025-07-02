package me.wait_for_meee.deathEffectPlugin.listener;

import me.wait_for_meee.deathEffectPlugin.core.EffectManager;
import me.wait_for_meee.deathEffectPlugin.model.DeathEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public final class DeathListener implements Listener {

    private final EffectManager effectManager;

    public DeathListener(EffectManager effectManager) {
        this.effectManager = effectManager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getPlayer();

        DeathEffect deathEffect = effectManager.getEffect(player.getUniqueId());

        if (deathEffect != null) {
            deathEffect.displayDeathEffect();
        }
    }

}
