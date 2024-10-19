package codes.nw.nosnow;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfoCommand implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        ConfigHandler configHandler = new ConfigHandler();

        if (args.length == 0) {

            sender.sendMessage(TextPrefix.PrefixedMessage("---------------------------"));
            sender.sendMessage(TextPrefix.PrefixedMessage("NoSnow v1.0.3"));
            sender.sendMessage(TextPrefix.PrefixedMessage(""));

            List<Material> blockNames = configHandler.returnBlockTypes(configHandler.readFromConfig());

            if (blockNames.size() == 0) {
                sender.sendMessage(
                        TextPrefix.PrefixedMessage("No blocks are currently set to prevent snow from forming"));
            } else {
                sender.sendMessage(TextPrefix.PrefixedMessage("Blocks that will prevent snow from forming:"));
                // empty line
                sender.sendMessage(TextPrefix.PrefixedMessage(""));
                for (Material blockName : blockNames) {
                    sender.sendMessage(TextPrefix.PrefixedMessage(ChatColor.RED + blockName.toString()));
                }
            }
            sender.sendMessage(TextPrefix.PrefixedMessage("---------------------------"));

        }
        return true;
    }
}
