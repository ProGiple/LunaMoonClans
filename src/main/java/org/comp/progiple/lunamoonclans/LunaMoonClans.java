package org.comp.progiple.lunamoonclans;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.comp.progiple.lunamoonclans.Clans.Clan;

import java.io.File;
import java.util.Objects;

public final class LunaMoonClans extends JavaPlugin {
    @Getter private static LunaMoonClans plugin;

    @Override
    public void onEnable() {
        plugin = this;

        File dir = new File(plugin.getDataFolder(), "clans");
        if (dir.exists() && dir.isDirectory()) for (File file : Objects.requireNonNull(dir.listFiles())) {
            String fileName = file.getName();
            if (fileName.contains(".json")) {
                String patch = fileName.replace(".json", "");
                new Clan(patch);
            }
        }
    }

    @Override
    public void onDisable() {}
}
