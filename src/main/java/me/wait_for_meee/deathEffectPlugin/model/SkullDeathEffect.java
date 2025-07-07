package me.wait_for_meee.deathEffectPlugin.model;

import me.wait_for_meee.deathEffectPlugin.model.task.SkullTask;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SkullDeathEffect extends DeathEffect{


    @Override
    public void displayDeathEffect(@NotNull Location location) {

        SkullTask.getSkullTaskScheduler().startSkullTask(new SkullTask(location));

        location.getWorld().playSound(location, Sound.ENTITY_ALLAY_ITEM_GIVEN,1F,0.5F);
        location.getWorld().playSound(location, Sound.ENTITY_ALLAY_ITEM_THROWN,1F,0.5F);
    }
}
