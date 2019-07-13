package me.redteapot.nametaghider;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NametagHiderPlugin extends JavaPlugin implements Listener {
    private String teamName;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);

        teamName = getConfig().getString("scoreboardTeam.name");

        prepareScoreboardTeam();
    }

    private void prepareScoreboardTeam() {
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        Team team = scoreboard.getTeam(teamName);
        if(team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }

        team.setDisplayName(getConfig().getString("scoreboardTeam.displayName"));
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
    }

    @Override
    public void onDisable() {

    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        scoreboard.getTeam(teamName).addEntry(evt.getPlayer().getDisplayName());
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent evt) {
        if(evt.getRightClicked() instanceof Player) {
            final Player target = (Player) evt.getRightClicked();
            final Player source = evt.getPlayer();
            final String message = getConfig().getString("nicknameFormat")
                    .replace("%nickname%", target.getDisplayName());

            source.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(message)
            );
        }
    }
}
