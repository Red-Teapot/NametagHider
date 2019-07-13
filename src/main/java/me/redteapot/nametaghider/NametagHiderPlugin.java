package me.redteapot.nametaghider;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NametagHiderPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        getLogger().info("Player login: " + evt.getPlayer().getDisplayName());
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent evt) {
        getLogger().info("Player interact entity: " + evt.getPlayer().getDisplayName() + " -> " + evt.getRightClicked().getClass().getName());
        evt.getPlayer().spigot().sendMessage(
                ChatMessageType.ACTION_BAR,
                new TextComponent("Keke: " + evt.getRightClicked().getClass().getName())
        );
    }
}
