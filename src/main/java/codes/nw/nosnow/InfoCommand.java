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
            String noSnowPrefix = String.format("%s[%sNoSnow%s] ", ChatColor.AQUA, ChatColor.WHITE, ChatColor.AQUA);

            sender.sendMessage(noSnowPrefix + ChatColor.WHITE + "---------------------------");
            sender.sendMessage(noSnowPrefix + ChatColor.WHITE + "NoSnow v1.0.0");
            sender.sendMessage(noSnowPrefix);

            List<Material> blockNames = configHandler.returnBlockTypes(configHandler.readFromConfig());

            if (blockNames.size() == 0) {
                sender.sendMessage(
                        noSnowPrefix + ChatColor.WHITE + "No blocks are currently set to prevent snow from forming");
            } else {
                sender.sendMessage(noSnowPrefix + ChatColor.WHITE + "Blocks that will prevent snow from forming:");
                // empty line
                sender.sendMessage(noSnowPrefix);
                for (Material blockName : blockNames) {
                    sender.sendMessage(noSnowPrefix + ChatColor.RED + blockName.toString());
                }
            }
            sender.sendMessage(noSnowPrefix + ChatColor.WHITE + "---------------------------");

        }
        return true;
    }
}
