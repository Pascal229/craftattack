package ch.molhx.craftattack2022;

import ch.molhx.craftattack2022.commands.StateCommand;
import ch.molhx.craftattack2022.events.JoinEvent;
import ch.molhx.craftattack2022.service.ConfigService;
import ch.molhx.craftattack2022.service.StateService;
import ch.molhx.craftattack2022.service.TablistService;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Craftattack2022 extends JavaPlugin {

    private static Craftattack2022 instance;
    private String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "CraftAttack" + ChatColor.DARK_GRAY + "] ";

    @Override
    public void onEnable() {
        instance = this;

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    }

    private void registerCommands() {
        getCommand("state").setExecutor(new StateCommand());
    }


    public String getPrefix() {
        return prefix;
    }

    public static Craftattack2022 getInstance() {return instance;}
}
