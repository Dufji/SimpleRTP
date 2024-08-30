package com.dufji.simpleRTP.listeners;

import com.dufji.simpleRTP.SimpleRTP;
import com.dufji.simpleRTP.utils.CC;
import com.dufji.simpleRTP.utils.LocationUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class RespawnListener implements Listener {

    private final SimpleRTP plugin;

    public RespawnListener(SimpleRTP plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        // Defining Variables
        Player player = event.getPlayer(); // Getting the player who died
        FileConfiguration config = plugin.getConfig(); // Get the config file

        Title title = Title.title(
                Component.text(
                        Objects.requireNonNull(CC.translate(config.getString("message.deathTitle.firstLine")))
                ),
                Component.text(
                                Objects.requireNonNull(CC.translate(config.getString(event.isBedSpawn() ?
                                        "message.deathTitle.secondLineAlt"
                                        :
                                        "message.deathTitle.secondLine"))
                                )
                )
        );

        // Checking if they have a bed spawn
        // And if the rtpOn.respawn is enabled
        if (!event.isBedSpawn()
                && config.getBoolean("rtpOn.respawn")) {


            // Teleport the player to a random location
            player.teleportAsync(LocationUtils.generateLocation(this.plugin, Objects.requireNonNull(plugin.getServer().getWorld("world")))).thenAccept(
                    (result) -> {
                        // If the title message is enabled
                        if (config.getBoolean("message.deathTitle.enabled"))
                            // Send the title message
                            player.showTitle(title);

                        if(config.getBoolean("message.chat.enabled"))
                            // send the chat message
                            player.sendMessage(
                                    CC.translate(
                                            Objects.requireNonNull(config.getString("message.chat.message"))
                                    )
                            );
                    }
            );
        }


        // Just shows the messages if they have a bed spawn
        if (config.getBoolean("message.deathTitle.enabled"))
            // Send the title message
            player.showTitle(title);

        if(config.getBoolean("message.chat.enabled"))
            // send the chat message
            player.sendMessage(
                    CC.translate(
                            Objects.requireNonNull(config.getString("message.chat.message"))
                    )
            );


    }
}
