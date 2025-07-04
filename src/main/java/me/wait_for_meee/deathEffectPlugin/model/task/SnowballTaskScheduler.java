package me.wait_for_meee.deathEffectPlugin.model.task;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class SnowballTaskScheduler implements IObserver<SnowballTask> {

    private Map<SnowballTask, BukkitTask> taskMap = new HashMap<>();

    @NotNull
    private final JavaPlugin plugin;

    public SnowballTaskScheduler(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void startSnowballTask(@NotNull SnowballTask snowballTask) {

        BukkitTask bukkitTask = plugin.getServer().getScheduler().runTaskTimer(plugin, snowballTask, 0,1);

        taskMap.put(snowballTask,bukkitTask);
    }

    public void endSnowballTask(@NotNull SnowballTask snowballTask) {

        BukkitTask bukkitTask = taskMap.get(snowballTask);

        if (bukkitTask != null && !bukkitTask.isCancelled()) {
            bukkitTask.cancel();
        }

        taskMap.remove(snowballTask);
    }

    public Collection<BukkitTask> getAllTasks() {
        return taskMap.values();
    }

    @Override
    public void update(@NotNull SnowballTask snowballTask) {
        endSnowballTask(snowballTask);
    }
}
