package codes.nw.nosnow;

import org.bukkit.ChatColor;

public class TextPrefix {
    public static String PrefixedMessage(String message) {
        return String.format("%s[%sNoSnow%s]%s %s", ChatColor.AQUA, ChatColor.WHITE, ChatColor.AQUA, ChatColor.WHITE,
                message);
    }

}
