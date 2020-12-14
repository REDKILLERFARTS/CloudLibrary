package net.cloud.library.support;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReflectionUtils {

    private String MINECRAFT_VERSION;
    private Pattern hexPattern = Pattern.compile("#[a-fA-F-0-9]{6}");

    public ReflectionUtils() {
        MINECRAFT_VERSION = Bukkit.getServer().getBukkitVersion().split("-")[0];
    }


    public String getColor(String args) {
        if(MINECRAFT_VERSION.contains("1.16")) {
            Matcher match = hexPattern.matcher(args);

            while(match.find()) {
                String color = args.substring(match.start(), match.end());
                args = args.replace(color, ChatColor.of(color) + "");
                match = hexPattern.matcher(args);
            }
        }

        return ChatColor.translateAlternateColorCodes('&', args);
    }

    private static ReflectionUtils utils;
    public static ReflectionUtils getUtils() {
        if(utils == null) utils = new ReflectionUtils();
        return utils;
    }
}
