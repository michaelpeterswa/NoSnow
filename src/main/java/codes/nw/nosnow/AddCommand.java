package codes.nw.nosnow;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddCommand implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        ConfigHandler configHandler = new ConfigHandler();

        if (args.length == 0) {
            sender.sendMessage(TextPrefix.PrefixedMessage("Please specify a block to prevent snow from forming on"));
        } else {
            List<Material> newBlocks = new ArrayList<>();
            for (String arg : args) {
                Material matchedMaterial = Material.matchMaterial(arg);
                if (matchedMaterial == null) {
                    sender.sendMessage(TextPrefix.PrefixedMessage("Invalid block type: " + arg));
                    continue;
                }
                if (newBlocks.contains(matchedMaterial)) {
                    continue;
                }
                newBlocks.add(matchedMaterial);
            }

            List<Material> currentBlocks = configHandler.returnBlockTypes(configHandler.readFromConfig());
            List<Material> addedBlocks = new ArrayList<>();

            for (Material block : newBlocks) {
                if (!currentBlocks.contains(block)) {
                    currentBlocks.add(block);
                    addedBlocks.add(block);
                }
            }

            configHandler.saveNewBlockTypes(currentBlocks);

            StringBuilder blockTypesString = new StringBuilder();
            addedBlocks.forEach(blockType -> blockTypesString.append(blockType.name()).append(","));
            // trim comma off end of string
            if (blockTypesString.length() > 0) {
                blockTypesString.setLength(blockTypesString.length() - 1);
            } else {
                sender.sendMessage(TextPrefix.PrefixedMessage("No new blocks added"));
                return true;
            }

            sender.sendMessage(
                    TextPrefix.PrefixedMessage(
                            "Added " + blockTypesString + " to the list of blocks that snow will not form on"));
        }

        return true;
    }
}
