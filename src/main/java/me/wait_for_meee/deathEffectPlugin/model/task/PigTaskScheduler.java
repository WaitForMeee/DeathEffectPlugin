package me.wait_for_meee.deathEffectPlugin.model.task;

import me.wait_for_meee.deathEffectPlugin.DeathEffectPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PigTaskScheduler implements IObserver<PigTask>{

    private final JavaPlugin plugin;

    public PigTaskScheduler(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private Map<PigTask, BukkitTask> taskMap = new HashMap<>();

    public void startPigTask(@NotNull PigTask pigTask) {

    }

    public void endPigTask(@NotNull PigTask pigTask) {

    }

    @Override
    public void update(@NotNull PigTask pigTask) {

    }
}
