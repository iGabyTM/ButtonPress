package me.gabytm.buttonpress;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Utils {

    public static String formatText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static long delay(Material buttonType) { return buttonType == Material.WOOD_BUTTON ? 30L : 20L; }
}
