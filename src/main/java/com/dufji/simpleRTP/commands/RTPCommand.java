package com.dufji.simpleRTP.commands;

import com.dufji.simpleRTP.SimpleRTP;
import com.dufji.simpleRTP.utils.CC;
import com.dufji.simpleRTP.utils.LocationUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Cooldown;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.util.Objects;

public class RTPCommand {

    private final SimpleRTP plugin;

    public RTPCommand(SimpleRTP plugin) {
        this.plugin = plugin;
    }

    @Command("rtp")
    @CommandPermission("simplertp.rtp")
    @Cooldown(60)
    @Description("Teleport to a random location")
    public void rtp(Player sender) {

        FileConfiguration config = plugin.getConfig(); // Get the config file

        Location generatedLoc = LocationUtils.generateLocation(this.plugin, sender.getWorld()); // Generate a location here

        // Teleport Effects
        sender.playSound(sender, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        sender.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));



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
        sender.teleportAsync(generatedLoc).thenAccept(
                (result) -> {
                    if (config.getBoolean("message.title.enabled"))
                        // send title message
                        sender.showTitle(title);


                    if (config.getBoolean("message.chat.enabled"))
                        // send chat message
                        sender.sendMessage(Objects.requireNonNull(CC.translate(config.getString("message.chat.message"))));

                }
        );


    }
}
