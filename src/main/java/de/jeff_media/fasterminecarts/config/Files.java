package de.jeff_media.fasterminecarts.config;

import de.jeff_media.fasterminecarts.FasterMinecarts;

import java.io.File;

public class Files {

    private static final FasterMinecarts main = FasterMinecarts.getInstance();

    public static final File MESSAGE_YML = new File(main.getDataFolder(), "messages.yml");
}
