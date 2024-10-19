package codes.nw.nosnow;

import org.bukkit.ChatColor;
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

            String[] blockNames = configHandler.returnBlockNames(configHandler.readFromConfig());
            sender.sendMessage(noSnowPrefix + ChatColor.WHITE + blockNames.length);

            if (blockNames.length == 0) {
                sender.sendMessage(
                        noSnowPrefix + ChatColor.RED + "No blocks are currently set to prevent snow from forming");
            } else {
                sender.sendMessage(noSnowPrefix + ChatColor.RED + "Blocks that will prevent snow from forming:");
                for (String blockName : blockNames) {
                    sender.sendMessage(noSnowPrefix + ChatColor.RED + blockName);
                }
            }
            sender.sendMessage(noSnowPrefix + ChatColor.WHITE + "---------------------------");

        }
        return true;
    }
}
