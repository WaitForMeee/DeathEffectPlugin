package me.wait_for_meee.deathEffectPlugin.model.task;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class PigTaskScheduler implements IObserver<PigTask> {

    @NotNull
    private final JavaPlugin plugin;

    public PigTaskScheduler(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private Map<PigTask, BukkitTask> taskMap = new HashMap<>();

    public void startPigTask(@NotNull PigTask pigTask) {

        BukkitTask bukkitTask = plugin.getServer().getScheduler().runTaskTimer(plugin, pigTask, 0,1);

        taskMap.put(pigTask,bukkitTask);
    }

    public void endPigTask(@NotNull PigTask pigTask) {

        BukkitTask bukkitTask = taskMap.get(pigTask);

        if (bukkitTask != null && !bukkitTask.isCancelled()) {
            bukkitTask.cancel();
        }

        taskMap.remove(pigTask);
    }

    public Collection<BukkitTask> getAllTasks() {
        return taskMap.values();
    }

    @Override
    public void update(@NotNull PigTask pigTask) {
        this.endPigTask(pigTask);
    }
}
