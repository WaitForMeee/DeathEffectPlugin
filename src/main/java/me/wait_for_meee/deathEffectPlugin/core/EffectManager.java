package me.wait_for_meee.deathEffectPlugin.core;

import me.wait_for_meee.deathEffectPlugin.model.DeathEffect;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class EffectManager {

    private Map<UUID, DeathEffect> effectMap;

    private File file;
    private JavaPlugin plugin;
    private YamlConfiguration config;

    public EffectManager(final JavaPlugin plugin) {
        this.plugin = plugin;
        effectMap = new HashMap<>();
    }

    public void load() {

        file = new File(plugin.getDataFolder(),"effects.yml");

        if (!file.exists()) {
            plugin.saveResource("effects.yml",false);
        }

        config = new YamlConfiguration();

        try {
            config.load(file);
        } catch (final Throwable t) {
            t.printStackTrace();
        }

        ConfigurationSection section = config.getConfigurationSection("effects_map");

        if (section == null) {
            throw new NullPointerException("No section \"effects_map\" in effects.yml")
        }

        for (Map.Entry<UUID,DeathEffect> entry : effectMap.entrySet()) {

        }
    }

    public void save() {

        try {
            config.save(file);
        } catch (final Throwable t) {
            t.printStackTrace();
        }
    }

    public void setEffect(UUID uuid, DeathEffect deathEffect) {

        ConfigurationSection section = config.getConfigurationSection("effects_map");

        try {
            section.set(uuid.toString(), deathEffect.toString());
        } catch (final NullPointerException e) {
            throw new NullPointerException("No section \"effects_map\" in effects.yml");
        }
    }
}
