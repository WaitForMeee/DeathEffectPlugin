package me.wait_for_meee.deathEffectPlugin.model;

import me.wait_for_meee.deathEffectPlugin.model.task.SkullTask;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SkullDeathEffect extends DeathEffect{

    public SkullDeathEffect(@NotNull OfflinePlayer player) {
        super(player);
    }


    @Override
    public void displayDeathEffect() {

        if (!player.isOnline())
            return;

        Player p = (Player) player;

        Location loc = p.getLocation();

        SkullTask.getSkullTaskScheduler().startSkullTask(new SkullTask(loc));

        loc.getWorld().playSound(loc, Sound.ENTITY_ALLAY_ITEM_GIVEN,1F,0.5F);
        loc.getWorld().playSound(loc, Sound.ENTITY_ALLAY_ITEM_THROWN,1F,0.5F);
    }
}
