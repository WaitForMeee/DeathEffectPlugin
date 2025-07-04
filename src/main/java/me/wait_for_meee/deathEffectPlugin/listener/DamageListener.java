package me.wait_for_meee.deathEffectPlugin.listener;

import me.wait_for_meee.deathEffectPlugin.model.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (event.getEntity().getPersistentDataContainer().has(Util.pigPDC)) {
            event.setCancelled(true);
        }
    }
}
