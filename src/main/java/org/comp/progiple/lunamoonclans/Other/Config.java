package org.comp.progiple.lunamoonclans.Other;

import org.bukkit.configuration.file.FileConfiguration;
import org.comp.progiple.lunamoonclans.LunaMoonClans;

public class Config {
    private static final LunaMoonClans plugin = LunaMoonClans.getPlugin();
    private static FileConfiguration config = plugin.getConfig();

    public static void reload() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public static int getInt(String path) {
        return config.getInt(path);
    }
}
