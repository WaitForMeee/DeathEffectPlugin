package me.wait_for_meee.deathEffectPlugin;

import me.wait_for_meee.deathEffectPlugin.command.EffectCommand;
import me.wait_for_meee.deathEffectPlugin.core.EffectManager;
import me.wait_for_meee.deathEffectPlugin.listener.*;
import me.wait_for_meee.deathEffectPlugin.model.task.PigTask;
import me.wait_for_meee.deathEffectPlugin.model.task.PigTaskScheduler;
import me.wait_for_meee.deathEffectPlugin.model.task.SnowballTask;
import me.wait_for_meee.deathEffectPlugin.model.task.SnowballTaskScheduler;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathEffectPlugin extends JavaPlugin {

    private EffectManager effectManager;
    private PigTaskScheduler pigTaskScheduler;
    private SnowballTaskScheduler snowballTaskScheduler;

    @Override
    public void onEnable() {

        effectManager = new EffectManager(this);
        pigTaskScheduler = new PigTaskScheduler(this);
        snowballTaskScheduler = new SnowballTaskScheduler(this);

        PigTask.setPigTaskScheduler(pigTaskScheduler);
        SnowballTask.setSnowballScheduler(snowballTaskScheduler);

        effectManager.load();

        getCommand("effect").setExecutor(new EffectCommand(effectManager));

        getServer().getPluginManager().registerEvents(new DeathListener(effectManager),this);
        getServer().getPluginManager().registerEvents(new ProjectileListener(),this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
    }

    @Override
    public void onDisable() {

        pigTaskScheduler.getAllTasks().stream().forEach(task -> {
            if (task != null && !task.isCancelled()) {
                task.cancel();
            }
        });
    }
}
