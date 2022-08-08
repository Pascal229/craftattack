package ch.molhx.craftattack2022.commands;

import ch.molhx.craftattack2022.Craftattack2022;
import ch.molhx.craftattack2022.service.StateService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        StateService stateService = Craftattack2022.getInstance().getStateService();

        if(args.length == 0) {
            if(stateService.getState(player).equals("")) {
                player.sendMessage(Craftattack2022.getInstance().getPrefix() + ChatColor.RED + "Usage: /state <state>");
                return true;
            }

            player.sendMessage(Craftattack2022.getInstance().getPrefix() + ChatColor.GRAY + "Your current state is: " + stateService.getState(player) + ".");
            return true;
        }

        String state = args[0];

        if(state.length() > 20) {
            player.sendMessage(Craftattack2022.getInstance().getPrefix() + ChatColor.RED + "State name has to be shorter than 20 characters!");
            return true;
        }

        if(stateService.getState(player).equals(state)) {
            player.sendMessage(Craftattack2022.getInstance().getPrefix() + ChatColor.RED + "State is already set to " + state);
            return true;
        }

        stateService.setState(player, state);

        player.sendMessage(Craftattack2022.getInstance().getPrefix() + ChatColor.GREEN + "Sucessfully set!");

        return true;
    }
}
