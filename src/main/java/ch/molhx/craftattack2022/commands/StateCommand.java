package ch.molhx.craftattack2022.commands;

import ch.molhx.craftattack2022.Craftattack2022;
import ch.molhx.craftattack2022.service.StateService;
import ch.molhx.craftattack2022.service.TablistService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class StateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        StateService stateService = new StateService();
        TablistService tablistService = new TablistService();
        String prefix = Craftattack2022.getInstance().getPrefix();

        if(args.length == 0 || args.length > 1) {
            if(stateService.getState(player) == null) {
                player.sendMessage(prefix + ChatColor.RED + "Usage: /state <state>");
                return true;
            }

            player.sendMessage(prefix + ChatColor.GRAY + "Your current state is: " + stateService.getState(player) + ".");
            return true;
        }

        String newState = args[0];

        if(newState.replaceAll("&[a-z-0-9]", "").length() > 20) {
            player.sendMessage(prefix + ChatColor.RED + "State name has to be shorter than 20 characters!");
            return true;
        }

        if(stateService.getState(player) != null && stateService.getState(player).equals(newState)) {
            player.sendMessage(prefix + ChatColor.RED + "State is already set to " + newState);
            return true;
        }

        stateService.setState(player, newState);
        tablistService.setAllPlayerTeams();

        player.sendMessage(prefix + ChatColor.GREEN + "Sucessfully set!");

        return true;
    }
}
