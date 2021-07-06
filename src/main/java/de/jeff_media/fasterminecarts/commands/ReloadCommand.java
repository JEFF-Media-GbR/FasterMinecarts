package de.jeff_media.fasterminecarts.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import de.jeff_media.fasterminecarts.FasterMinecarts;
import de.jeff_media.fasterminecarts.config.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static de.jeff_media.fasterminecarts.config.Messages.sendMessage;

@CommandAlias("fasterminecarts")
public class ReloadCommand extends BaseCommand {

    private static final FasterMinecarts main = FasterMinecarts.getInstance();

    @Default
    @HelpCommand
    public static void onHelp(CommandSender sender, String[] args) {
        sendMessage(sender, "&6&l[&a&lFaster&2&lMinecarts&6&l]&r &6Available commands:");
        sendMessage(sender, "&6help &r- Shows this help text");
        sendMessage(sender, "&6reload &r- Reloads the configuration");
    }

    @Subcommand("reload")
    @CommandPermission("fasterminecarts.reload")
    @Description("Reloads FasterMinecarts")
    public static void onReload(CommandSender sender, String[] args) {
        main.loadConfig();
        sendMessage(sender, Messages.RELOAD_COMPLETE);
    }
}
