package com.dufji.simpleRTP;

import com.dufji.simpleRTP.commands.RTPCommand;
import com.dufji.simpleRTP.listeners.JoinListener;
import com.dufji.simpleRTP.listeners.RespawnListener;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

public final class SimpleRTP extends JavaPlugin {

    @Override
    public void onEnable() {
        BukkitCommandHandler handler = BukkitCommandHandler.create(this);


        // config thingies
        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdir()) {
                getLogger().severe("Failed to create plugin data folder!");
            }
        }
        saveDefaultConfig();

        // Registering the command(s)
        handler.register(
                new RTPCommand(this)
        );



        // Registering the listener(s)
        getServer().getPluginManager().registerEvents(new RespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
