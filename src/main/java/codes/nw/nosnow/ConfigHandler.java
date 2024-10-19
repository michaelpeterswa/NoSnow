package codes.nw.nosnow;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
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

    public List<Material> returnBlockTypes(FileConfiguration config) {
        String blocks = config.getString("blocks");
        if (blocks == null) {
            return new ArrayList<>();
        }

        String[] splitBlocks = blocks.split(",");

        for (int i = 0; i < splitBlocks.length; i++) {
            splitBlocks[i] = splitBlocks[i].trim();
        }

        List<Material> blockTypesList = new ArrayList<>();
        for (String blockName : splitBlocks) {
            Material material = Material.matchMaterial(blockName);
            if (material != null) {
                blockTypesList.add(material);
            }
        }

        return blockTypesList;
    }

}
