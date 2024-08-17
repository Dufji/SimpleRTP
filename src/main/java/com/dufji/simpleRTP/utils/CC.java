package com.dufji.simpleRTP.utils;

import net.md_5.bungee.api.ChatColor;

public class CC {
    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String translate(org.bukkit.ChatColor color, String message) {
        return toBungee(color) + message;
    }

    public static String colorCodes(String msg) {
        msg = msg.replace("&0", ChatColor.BLACK + "");
        msg = msg.replace("&1", ChatColor.DARK_BLUE + "");
        msg = msg.replace("&2", ChatColor.DARK_GREEN + "");
        msg = msg.replace("&3", ChatColor.DARK_AQUA + "");
        msg = msg.replace("&4", ChatColor.DARK_RED + "");
        msg = msg.replace("&5", ChatColor.DARK_PURPLE + "");
        msg = msg.replace("&6", ChatColor.GOLD + "");
        msg = msg.replace("&7", ChatColor.GRAY + "");
        msg = msg.replace("&8", ChatColor.DARK_GRAY + "");
        msg = msg.replace("&9", ChatColor.BLUE + "");
        msg = msg.replace("&a", ChatColor.GREEN + "");
        msg = msg.replace("&b", ChatColor.AQUA + "");
        msg = msg.replace("&c", ChatColor.RED + "");
        msg = msg.replace("&d", ChatColor.LIGHT_PURPLE + "");
        msg = msg.replace("&e", ChatColor.YELLOW + "");
        msg = msg.replace("&f", ChatColor.WHITE + "");
        msg = msg.replace("&l", ChatColor.BOLD + "");
        msg = msg.replace("&m", ChatColor.STRIKETHROUGH + "");
        msg = msg.replace("&n", ChatColor.UNDERLINE + "");
        msg = msg.replace("&o", ChatColor.ITALIC + "");
        msg = msg.replace("&r", ChatColor.RESET + "");
        return msg;
    }

    public static net.md_5.bungee.api.ChatColor toBungee(org.bukkit.ChatColor color) {
        switch (color) {
            case BLACK:
                return net.md_5.bungee.api.ChatColor.BLACK;
            case DARK_BLUE:
                return net.md_5.bungee.api.ChatColor.DARK_BLUE;
            case DARK_GREEN:
                return net.md_5.bungee.api.ChatColor.DARK_GREEN;
            case DARK_AQUA:
                return net.md_5.bungee.api.ChatColor.DARK_AQUA;
            case DARK_RED:
                return net.md_5.bungee.api.ChatColor.DARK_RED;
            case DARK_PURPLE:
                return net.md_5.bungee.api.ChatColor.DARK_PURPLE;
            case GOLD:
                return net.md_5.bungee.api.ChatColor.GOLD;
            case GRAY:
                return net.md_5.bungee.api.ChatColor.GRAY;
            case DARK_GRAY:
                return net.md_5.bungee.api.ChatColor.DARK_GRAY;
            case BLUE:
                return net.md_5.bungee.api.ChatColor.BLUE;
            case GREEN:
                return net.md_5.bungee.api.ChatColor.GREEN;
            case AQUA:
                return net.md_5.bungee.api.ChatColor.AQUA;
            case RED:
                return net.md_5.bungee.api.ChatColor.RED;
            case LIGHT_PURPLE:
                return net.md_5.bungee.api.ChatColor.LIGHT_PURPLE;
            case YELLOW:
                return net.md_5.bungee.api.ChatColor.YELLOW;
            case WHITE:
                return net.md_5.bungee.api.ChatColor.WHITE;
            case MAGIC:
                return net.md_5.bungee.api.ChatColor.MAGIC;
            case BOLD:
                return net.md_5.bungee.api.ChatColor.BOLD;
            case STRIKETHROUGH:
                return net.md_5.bungee.api.ChatColor.STRIKETHROUGH;
            case UNDERLINE:
                return net.md_5.bungee.api.ChatColor.UNDERLINE;
            case ITALIC:
                return net.md_5.bungee.api.ChatColor.ITALIC;
            case RESET:
                return net.md_5.bungee.api.ChatColor.RESET;
            default:
                throw new IllegalArgumentException("Unrecognised Bukkit colour " + color.name() + ".");
        }
    }
}
