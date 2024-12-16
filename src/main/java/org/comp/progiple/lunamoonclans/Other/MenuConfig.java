package org.comp.progiple.lunamoonclans.Other;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.comp.progiple.lunamoonclans.LunaMoonClans;
import org.comp.progiple.lunamoonclans.Menu.MenuId;

import java.io.File;
import java.util.List;

public class MenuConfig {
    private final File file;
    private FileConfiguration config;
    public MenuConfig(MenuId menuId) {
        this.file = new File(LunaMoonClans.getPlugin().getDataFolder(), String.format("menus/%s.yml", menuId.name()));
        this.reload();
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean getBool(String path) {
        return config.getBoolean(path);
    }

    public String getStr(String path) {
        return config.getString(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public List<String> getStrList(String path) {
        return config.getStringList(path);
    }

    public List<Integer> getIntList(String path) {
        return config.getIntegerList(path);
    }

    public ConfigurationSection getSection(String path) {
        return config.getConfigurationSection(path);
    }
}
