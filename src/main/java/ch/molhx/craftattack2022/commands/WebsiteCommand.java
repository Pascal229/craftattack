package ch.molhx.craftattack2022.commands;

import ch.molhx.craftattack2022.Craftattack2022;
import ch.molhx.craftattack2022.service.ConfigService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebsiteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        ConfigService configService = new ConfigService();
        String prefix = Craftattack2022.getInstance().getPrefix();
        Player player = (Player) sender;

        Object link = configService.get("website");
        if(link == null) {
            player.sendMessage(prefix + ChatColor.RED + "Website not in Config!");
            return true;
        }

        player.sendMessage(prefix  + ChatColor.GRAY + link.toString());
        return true;
    }
}
