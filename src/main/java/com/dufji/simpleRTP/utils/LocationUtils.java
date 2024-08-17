package com.dufji.simpleRTP.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LocationUtils {

    private static final Random random = new Random();


    public static Location generateLocation(Plugin plugin, World world) {
        FileConfiguration config = plugin.getConfig();

        // Cache configuration values
        Set<String> unsafeMaterials = new HashSet<>(config.getStringList("unsafeBlocks"));
        boolean negative = config.getBoolean("rtpLimit.negative");
        boolean worldBorder = config.getBoolean("rtpLimit.worldBorder");

        int xLimit = worldBorder ? (int) world.getWorldBorder().getSize() / 2 : config.getInt("rtpLimit.xLimit");
        int zLimit = worldBorder ? (int) world.getWorldBorder().getSize() / 2 : config.getInt("rtpLimit.zLimit");

        int x, z;
        Block generatedBlock, higherBlock;
        String generatedMaterial, higherMaterial;

        while (true) {
            x = random.nextInt(xLimit) * (negative ? 2 : 1) - (negative ? xLimit : 0);
            z = random.nextInt(zLimit) * (negative ? 2 : 1) - (negative ? zLimit : 0);

            generatedBlock = world.getHighestBlockAt(x, z);
            higherBlock = world.getBlockAt(x, generatedBlock.getY() + 2, z);

            generatedMaterial = generatedBlock.getBlockData().getMaterial().name();
            higherMaterial = higherBlock.getBlockData().getMaterial().name();

            if (!unsafeMaterials.contains(generatedMaterial) && !unsafeMaterials.contains(higherMaterial)) {
                return new Location(world, x + 0.5, generatedBlock.getY() + 1, z + 0.5);
            }
        }
    }




}
