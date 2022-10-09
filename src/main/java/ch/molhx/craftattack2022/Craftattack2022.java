package ch.molhx.craftattack2022;

import ch.molhx.craftattack2022.commands.PlayTimeCommand;
import ch.molhx.craftattack2022.commands.StateCommand;
import ch.molhx.craftattack2022.commands.WebsiteCommand;
import ch.molhx.craftattack2022.events.BedEvent;
import ch.molhx.craftattack2022.events.ChatEvent;
import ch.molhx.craftattack2022.events.JoinEvent;
import ch.molhx.craftattack2022.events.ServerPingEvent;
import ch.molhx.craftattack2022.service.ConfigService;
import ch.molhx.craftattack2022.service.PlaytimeService;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Craftattack2022 extends JavaPlugin {

    private static Craftattack2022 instance;
    private String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "CraftAttack" + ChatColor.DARK_GRAY + "] ";

    private PlaytimeService playtimeService = new PlaytimeService();

    @Override
    public void onEnable() {
        instance = this;

        this.registerListeners();
        this.registerCommands();

        this.playtimeService.start();

        ConfigService configService = new ConfigService();
        configService.set("version", "1.0.0");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.playtimeService.stop();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new BedEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new ServerPingEvent(), this);
    }

    private void registerCommands() {
        getCommand("playtime").setExecutor(new PlayTimeCommand());
        getCommand("state").setExecutor(new StateCommand());
        getCommand("website").setExecutor(new WebsiteCommand());
    }

    public String getPrefix() {
        return prefix;
    }

    public static Craftattack2022 getInstance() {return instance;}
}
