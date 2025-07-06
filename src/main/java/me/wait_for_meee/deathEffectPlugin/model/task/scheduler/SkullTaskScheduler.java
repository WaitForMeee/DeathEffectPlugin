package me.wait_for_meee.deathEffectPlugin.model.task.scheduler;

import me.wait_for_meee.deathEffectPlugin.model.task.IObserver;
import me.wait_for_meee.deathEffectPlugin.model.task.SkullTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class SkullTaskScheduler implements IObserver<SkullTask> {

    @NotNull
    private final JavaPlugin plugin;

    private Map<SkullTask, BukkitTask> taskMap = new HashMap<>();

    public SkullTaskScheduler(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void startSkullTask(@NotNull SkullTask skullTask) {

        BukkitTask bukkitTask = plugin.getServer().getScheduler().runTaskTimer(plugin, skullTask,0,1);

        taskMap.put(skullTask,bukkitTask);
    }

    public void endSkullTask(@NotNull SkullTask skullTask) {

        BukkitTask bukkitTask = taskMap.get(skullTask);

        if (bukkitTask != null && !bukkitTask.isCancelled()) {
            bukkitTask.cancel();
        }

        taskMap.remove(skullTask);
    }

    @Override
    public void update(@NotNull SkullTask skullTask) {
        endSkullTask(skullTask);
    }
}
