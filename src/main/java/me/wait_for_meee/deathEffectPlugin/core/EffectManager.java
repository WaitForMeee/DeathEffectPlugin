package me.wait_for_meee.deathEffectPlugin.core;

import me.wait_for_meee.deathEffectPlugin.DeathEffectPlugin;
import me.wait_for_meee.deathEffectPlugin.model.DeathEffect;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
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
            throw new NullPointerException("No section \"effects_map\" in effects.yml");
        }

        for (Map.Entry<String, Object> entry : section.getValues(false).entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue().toString();

            try {
                UUID uuid = UUID.fromString(key);
                OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);

                Class<? extends DeathEffect> clazz = DeathEffect.getByString(value);
                if (clazz == null) {
                    DeathEffectPlugin.getInstance().getLogger().warning("(effects.yml) Invalid death effect at " + key + ": " + value);
                    continue;
                }

                DeathEffect effect = clazz.getDeclaredConstructor(OfflinePlayer.class).newInstance(player);
                effectMap.put(uuid, effect);

            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                DeathEffectPlugin.getInstance().getLogger().warning("(effects.yml) Failed to instantiate " + value + " for player " + key);
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                DeathEffectPlugin.getInstance().getLogger().warning("(effects.yml) Invalid UUID format: " + key);
            }
        }
    }

    public void save() {

        try {
            config.save(file);
        } catch (final Throwable t) {
            t.printStackTrace();
        }
    }

    public void disableEffect(UUID uuid) {

        effectMap.put(uuid,null);

        ConfigurationSection section = config.getConfigurationSection("effects_map");

        if (section == null) {
            config.createSection("effects_map");
        }

        section.set(uuid.toString(),null);
        save();
    }

    public void setEffect(UUID uuid, DeathEffect deathEffect) {

        effectMap.put(uuid,deathEffect);

        ConfigurationSection section = config.getConfigurationSection("effects_map");

        if (section == null) {
            config.createSection("effects_map");
        }

        section.set(uuid.toString(),deathEffect.toString());
        save();
    }

    @Nullable
    public DeathEffect getEffect(UUID uuid) {
        return effectMap.get(uuid);
    }
}
