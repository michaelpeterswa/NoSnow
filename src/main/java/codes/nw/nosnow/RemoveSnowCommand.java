package codes.nw.nosnow;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveSnowCommand implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(TextPrefix.PrefixedMessage("Please specify a radius to remove snow around (spherical)"));
        } else {
            int radius = args[0].matches("\\d+") ? Integer.parseInt(args[0]) : -1;
            if (radius == -1) {
                sender.sendMessage(TextPrefix.PrefixedMessage("Invalid radius: " + args[0]));
                return true;
            }

            Player player = Bukkit.getPlayerExact(sender.getName());

            int counter = 0;
            for (int x = radius; x >= -radius; x--) {
                for (int y = radius; y >= -radius; y--) {
                    for (int z = radius; z >= -radius; z--) {
                        // spherical check
                        if (Math.sqrt(x * x + y * y + z * z) > radius) {
                            continue;
                        }
                        Block block = player.getLocation().add(x, y, z).getBlock();
                        Material blockType = block.getType();
                        if (blockType == Material.SNOW ||
                                blockType == Material.SNOW_BLOCK ||
                                blockType == Material.POWDER_SNOW) {
                            block.setType(Material.AIR);
                            counter++;
                        }
                    }
                }
            }

            sender.sendMessage(TextPrefix.PrefixedMessage("Removed " + counter + " snow blocks"));
        }

        return true;
    }
}
