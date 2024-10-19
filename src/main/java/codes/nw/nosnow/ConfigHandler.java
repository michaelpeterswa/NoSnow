package codes.nw.nosnow;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigHandler {

    JavaPlugin plugin = NoSnow.getInst();

    void saveDefaultConfig() {
        plugin.saveDefaultConfig();
    }

    public FileConfiguration readFromConfig() {
        return plugin.getConfig();
    }

    public String[] returnBlockNames(FileConfiguration config) {
        String blocks = config.getString("blocks");
        if (blocks == null) {
            return new String[0];
        }

        return blocks.split(",");
    }

}
