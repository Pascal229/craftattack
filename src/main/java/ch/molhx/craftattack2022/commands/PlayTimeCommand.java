package ch.molhx.craftattack2022.commands;

import ch.molhx.craftattack2022.Craftattack2022;
import ch.molhx.craftattack2022.service.PlaytimeService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayTimeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        PlaytimeService playtimeService = new PlaytimeService();
        String prefix = Craftattack2022.getInstance().getPrefix();

        if(args.length == 0) {
            int playtime = playtimeService.getPlaytime(player);
            String playtimeString = playtimeService.formatPlaytime(playtime);

            player.sendMessage(prefix + ChatColor.GRAY + "Your playtime is: " + ChatColor.GREEN + playtimeString + ChatColor.GRAY + ".");
            return true;
        }

        if(args.length == 1) {

            for(OfflinePlayer player1 : Bukkit.getOfflinePlayers()) {
                if(player1.getName().equals(args[0])) {
                    int playtime = playtimeService.getPlaytimeByUUID(player1.getUniqueId().toString());
                    String playtimeString = playtimeService.formatPlaytime(playtime);

                    player.sendMessage(prefix + ChatColor.GRAY + player1.getName() + "'s playtime is: " + ChatColor.GREEN + playtimeString + ChatColor.GRAY + ".");
                    return true;
                }
            }


            player.sendMessage(prefix + ChatColor.RED + "Player not found!");
            return true;
        }

        player.sendMessage(prefix + ChatColor.RED + "Usage: /playtime <player>");
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> players = new ArrayList<>();
            for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                players.add(player.getName());
            }
            return players;
        }
        return null;
    }
}
