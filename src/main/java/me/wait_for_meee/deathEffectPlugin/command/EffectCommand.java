package me.wait_for_meee.deathEffectPlugin.command;

import me.wait_for_meee.deathEffectPlugin.core.EffectManager;
import me.wait_for_meee.deathEffectPlugin.model.DeathEffect;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class EffectCommand implements CommandExecutor, TabExecutor {

    @NotNull
    private final EffectManager effectManager;

    public EffectCommand(@NotNull EffectManager effectManager) {
        this.effectManager = effectManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You must be a player to use this command!");
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length == 0) {
            player.sendMessage(Component.text("/effect ").color(NamedTextColor.GREEN).append(Component.text("<set|disable|get>")));
            return true;
        }



       //disable
       if (strings[0].equalsIgnoreCase("disable")) {
           effectManager.disableEffect(player.getUniqueId());
           player.sendMessage(Component.text("[").color(NamedTextColor.WHITE)
                   .append(Component.text("✔").color(NamedTextColor.GREEN))
                   .append(Component.text("] You have ").color(NamedTextColor.WHITE))
                   .append(Component.text("disabled").color(NamedTextColor.RED))
                   .append(Component.text(" your death effect!")));
           return true;
       }

       //set
        if (strings[0].equalsIgnoreCase("set")) {

            //no effect provided
            if (strings.length == 1) {
                player.sendMessage(Component.text("[").color(NamedTextColor.WHITE)
                        .append(Component.text("✖").color(NamedTextColor.RED))
                        .append(Component.text("] Please, provide the death effect!").color(NamedTextColor.WHITE)));
                return true;
            }

            //effect provided
            if (strings.length == 2) {

                String arg = strings[1];

                Class<? extends DeathEffect> clazz = DeathEffect.getByShortName(arg);

                if (clazz == null) {

                    player.sendMessage(Component.text("[").color(NamedTextColor.WHITE)
                            .append(Component.text("✖").color(NamedTextColor.RED))
                            .append(Component.text("] You provided an invalid death effect!").color(NamedTextColor.WHITE)));

                    return true;
                }

                Constructor<? extends DeathEffect> constructor;

                try {
                    constructor = clazz.getDeclaredConstructor(OfflinePlayer.class);
                } catch (final Exception e) {
                    e.printStackTrace();
                    return true;
                }

                try {
                    effectManager.setEffect(player.getUniqueId(), constructor.newInstance(player));
                } catch (final Exception e) {
                    e.printStackTrace();
                }

                player.sendMessage(Component.text("[").color(NamedTextColor.WHITE)
                        .append(Component.text("✔").color(NamedTextColor.GREEN))
                        .append(Component.text("] You have ").color(NamedTextColor.WHITE))
                        .append(Component.text("set").color(NamedTextColor.GREEN))
                        .append(Component.text(" your death effect to "))
                        .append(Component.text(arg).color(NamedTextColor.YELLOW)));
            }

        }

        //get
        if (strings.length == 1 && strings[0].equalsIgnoreCase("get")) {

            DeathEffect deathEffect = effectManager.getEffect(player.getUniqueId());

            if (deathEffect == null) {
                player.sendMessage(Component.text("[").color(NamedTextColor.WHITE)
                        .append(Component.text("✔").color(NamedTextColor.GREEN))
                        .append(Component.text("] You have not set your death effect yet!").color(NamedTextColor.WHITE)));
            } else {

                player.sendMessage(Component.text("[").color(NamedTextColor.WHITE)
                        .append(Component.text("✔").color(NamedTextColor.GREEN))
                        .append(Component.text("] Your death effect is ").color(NamedTextColor.WHITE))
                        .append(Component.text(deathEffect.toShortName()).color(NamedTextColor.YELLOW)));
            }

            return true;
        }




        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 1) {
            return Stream.of("set","disable","get").filter(arg -> arg.startsWith(strings[0])).collect(Collectors.toList());
        }

        if (strings.length == 2 && strings[0].equalsIgnoreCase("set")) {
            return Stream.of("Explosion","Pig","Snowball")
                    .filter(arg -> arg.startsWith(strings[1])).collect(Collectors.toList());
        }

        return List.of();
    }
}
