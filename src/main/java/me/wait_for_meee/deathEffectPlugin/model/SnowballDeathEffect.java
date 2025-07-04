package me.wait_for_meee.deathEffectPlugin.model;

import me.wait_for_meee.deathEffectPlugin.model.task.SnowballTask;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

//when a player dies, snowballs are thrown in random directions which cover the ground with snow when they hit it
public final class SnowballDeathEffect extends DeathEffect {

    public SnowballDeathEffect(@NotNull OfflinePlayer player) {
        super(player);
    }



    @Override
    public void displayDeathEffect() {

        for (int i = 0; i < 5; ++i) {
            SnowballTask.getSnowballTaskScheduler()
                    .startSnowballTask(new SnowballTask( ( (Player) player ).getLocation()) );
        }
    }
}
