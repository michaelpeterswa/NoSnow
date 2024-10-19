package codes.nw.nosnow;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

public class SnowBlockFormListener implements Listener {

    @EventHandler
    public void onSnowBlockForm(BlockFormEvent event) {
        if (event.getNewState().getType() == Material.SNOW) {
            // get block below forming snow
            Block bottomFaceBlock = event.getBlock().getRelative(BlockFace.DOWN);
            ConfigHandler configHandler = new ConfigHandler();
            for (Material blockName : configHandler.returnBlockTypes(configHandler.readFromConfig())) {
                if (bottomFaceBlock.getType() == blockName) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
