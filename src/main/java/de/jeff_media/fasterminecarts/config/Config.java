package de.jeff_media.fasterminecarts.config;

import de.jeff_media.fasterminecarts.FasterMinecarts;
import lombok.Getter;
import org.bukkit.ChatColor;

public class Config {

    private static Config instance;

    public static Config get() {
        return instance;
    }

    public static void init() {
        instance = new Config();
    }

    private static final FasterMinecarts main = FasterMinecarts.getInstance();

    @Getter private final boolean debug = main.getConfig().getBoolean("debug", false);
    @Getter private final boolean speedometer = main.getConfig().getBoolean("speedometer", false);
    @Getter private final String speedometerText = ChatColor.translateAlternateColorCodes('&',main.getConfig().getString("speedometer-text","&aSpeed: %.2f B/s"));
}
