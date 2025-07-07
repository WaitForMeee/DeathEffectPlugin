package me.wait_for_meee.deathEffectPlugin.model;

import me.wait_for_meee.deathEffectPlugin.model.task.PigTask;
import me.wait_for_meee.deathEffectPlugin.model.task.SnowballTask;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

//a pig flies up after the player's death and then explodes with firework burst
public final class PigDeathEffect extends DeathEffect {

    @Override
    public void displayDeathEffect(@NotNull Location location) {



        PigTask.getPigTaskScheduler()
                .startPigTask(new PigTask(location));

    }
}
