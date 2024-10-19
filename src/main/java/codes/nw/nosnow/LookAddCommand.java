package codes.nw.nosnow;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LookAddCommand implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        ConfigHandler configHandler = new ConfigHandler();

        Player player = Bukkit.getPlayerExact(sender.getName());

        // define transparent materials
        Set<Material> transparentMaterials = new HashSet<>();

        transparentMaterials.add(Material.AIR);
        transparentMaterials.add(Material.CAVE_AIR);
        transparentMaterials.add(Material.VOID_AIR);
        transparentMaterials.add(Material.WATER);
        transparentMaterials.add(Material.LAVA);
        transparentMaterials.add(Material.SNOW);
        // ----------------------------

        Block targetBlock = player.getTargetBlock(transparentMaterials, 10);

        if (targetBlock == null) {
            sender.sendMessage(TextPrefix.PrefixedMessage("No block targeted within 10 blocks"));
            return true;
        }

        List<Material> currentBlocks = configHandler.returnBlockTypes(configHandler.readFromConfig());

        if (currentBlocks.contains(targetBlock.getType())) {
            sender.sendMessage(TextPrefix.PrefixedMessage(targetBlock.getType()
                    + " is already in the list of blocks that snow will not form on"));
            return true;
        }
        currentBlocks.add(targetBlock.getType());

        configHandler.saveNewBlockTypes(currentBlocks);

        sender.sendMessage(
                TextPrefix.PrefixedMessage(
                        "Added " + targetBlock.getType() + " to the list of blocks that snow will not form on"));

        return true;
    }
}
