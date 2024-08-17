package com.dufji.simpleRTP.listeners;

import com.dufji.simpleRTP.SimpleRTP;
import com.dufji.simpleRTP.utils.CC;
import com.dufji.simpleRTP.utils.LocationUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class JoinListener implements Listener {

    private final SimpleRTP plugin;

    public JoinListener(SimpleRTP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // Defining the player variable
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        // Making sure that the player has not played before and that the firstJoinTeleport is
        // enabled from the config
        if(!(player.hasPlayedBefore()) && config.getBoolean("firstJoinTeleport")) {
            // Generating a location
            Location generatedLoc = LocationUtils.generateLocation(this.plugin, player.getWorld()); // Generate a location here


            // Teleport Effects
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));


            // Title message (does not change)
            Title title = Title.title(
                    Component.text(
                            Objects.requireNonNull(CC.translate(config.getString("message.title.firstLine")))
                    ),
                    Component.text(
                            Objects.requireNonNull(CC.translate(config.getString("message.title.secondLine")))
                    )
            );

            // Teleporting the player
            player.teleportAsync(generatedLoc).thenAccept(
                    (result) -> {
                        if (config.getBoolean("message.title.enabled"))
                            // send title message
                            player.showTitle(title);


                        if (config.getBoolean("message.chat.enabled"))
                            // send chat message
                            player.sendMessage(Objects.requireNonNull(CC.translate(config.getString("message.chat.message"))));

                    }
            );
        }
    }
}
