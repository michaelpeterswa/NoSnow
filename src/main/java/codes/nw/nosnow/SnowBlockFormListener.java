package codes.nw.nosnow;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

public class SnowBlockFormListener implements Listener {

    @EventHandler
    public void onSnowBlockForm(BlockFormEvent event) {
        // Bukkit.getLogger().info(event.getBlock().getRelative(0, -1,
        // 0).getType().toString());
        // Bukkit.getServer().broadcastMessage(event.getBlock().getRelative(0, -1,
        // 0).getType().toString());
        // get block below the snow block
        ConfigHandler configHandler = new ConfigHandler();
        for (String blockName : configHandler.returnBlockNames(configHandler.readFromConfig())) {
            // log block type

            if (event.getBlock().getRelative(0, -1, 0).getType().toString().contains(blockName)) {
                event.setCancelled(true);
            }
        }
    }
}
