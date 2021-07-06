package de.jeff_media.fasterminecarts.config;

import de.jeff_media.fasterminecarts.FasterMinecarts;
import de.jeff_media.jefflib.FileUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Messages {

    private static final FasterMinecarts main = FasterMinecarts.getInstance();
    private static final FileConfiguration config = new YamlConfiguration();

    public static String PREFIX, RELOAD_COMPLETE;

    public static void init() {
        try {
            config.load(new InputStreamReader(main.getResource("messages.yml")));
            config.load(Files.MESSAGE_YML);
            System.out.println("Messages loaded!");
        } catch (IOException | InvalidConfigurationException e) {
            main.getLogger().severe("Your messages.yml file is invalid! Default messages will be used instead.");
        }

        PREFIX = load("prefix",false);
        RELOAD_COMPLETE = load("reloaded");
    }

    public static void sendMessage(final @NotNull CommandSender receiver, final @Nullable String message) {
        if(message==null) return;
        receiver.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
    }

    @Nullable
    private static String load(final @NotNull String node) {
        return load(node, true);
    }

    @Nullable
    private static String load(final @NotNull String node, boolean addPrefix) {
        String message = config.getString(node);
        if(message == null || message.length()==0) return null;
        return addPrefix ? (PREFIX + message) : message;
    }
}
