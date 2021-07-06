package de.jeff_media.fasterminecarts;

import co.aikar.commands.BukkitCommandManager;
import de.jeff_media.fasterminecarts.commands.ReloadCommand;
import de.jeff_media.fasterminecarts.config.Config;
import de.jeff_media.fasterminecarts.config.Files;
import de.jeff_media.fasterminecarts.config.Messages;
import de.jeff_media.fasterminecarts.listeners.MinecartListener;
import de.jeff_media.fasterminecarts.listeners.SpeedometerListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Minecart;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Locale;

public class FasterMinecarts extends JavaPlugin {

    @Getter private static FasterMinecarts instance;

    @Override
    public void onLoad() {
        instance = this;
        createConfigFiles();
    }

    private void createConfigFiles() {
        if(!Files.MESSAGE_YML.exists()) {
            saveResource("messages.yml", false);
        }
    }

    public void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        Config.init();
        Messages.init();
    }

    @Override
    public void onEnable() {
        loadConfig();
        Bukkit.getPluginManager().registerEvents(new MinecartListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpeedometerListener(), this);
        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        commandManager.registerCommand(new ReloadCommand());
    }

    public double getMaxSpeed(Minecart minecart) {
        return getConfig().getDouble(minecart.getType().name().toLowerCase(Locale.ROOT).replace("_","-"), 8.0D)/20;
    }
}
