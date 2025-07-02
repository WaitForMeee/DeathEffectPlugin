package me.wait_for_meee.deathEffectPlugin;

import me.wait_for_meee.deathEffectPlugin.command.EffectCommand;
import me.wait_for_meee.deathEffectPlugin.core.EffectManager;
import me.wait_for_meee.deathEffectPlugin.listener.DeathListener;
import me.wait_for_meee.deathEffectPlugin.model.task.PigTask;
import me.wait_for_meee.deathEffectPlugin.model.task.PigTaskScheduler;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathEffectPlugin extends JavaPlugin {

    private EffectManager effectManager;
    private PigTaskScheduler pigTaskScheduler;

    @Override
    public void onEnable() {

        effectManager = new EffectManager(this);
        pigTaskScheduler = new PigTaskScheduler(this);
        PigTask.setPigTaskScheduler(pigTaskScheduler);

        effectManager.load();

        getCommand("effect").setExecutor(new EffectCommand(effectManager));

        getServer().getPluginManager().registerEvents(new DeathListener(effectManager),this);

    }

    @Override
    public void onDisable() {

    }
}
