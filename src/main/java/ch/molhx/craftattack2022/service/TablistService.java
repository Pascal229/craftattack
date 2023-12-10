package ch.molhx.craftattack2022.service;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Stack;

public class TablistService {
    public void setTablist(Player player) {
        PlaytimeService playtimeService = new PlaytimeService();
        int playtime = playtimeService.getPlaytime(player);
        String playtimeString = playtimeService.formatPlaytime(playtime);

        String header = "\n   " + ChatColor.BLUE + "§lCraftattack " + ChatColor.AQUA + "§l2023   \n";
        String footer = "\n   " + ChatColor.GRAY + "Your playtime: " + ChatColor.GREEN + playtimeString + "   \n";

        player.setPlayerListHeaderFooter(header, footer);
    }

    public void updateTablist(Player player) {
        setTablist(player);
    }

    public void setAllPlayerTeams() {
        Bukkit.getOnlinePlayers().forEach(this::setPlayerTeams);
    }

    public void setPlayerTeams(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        StateService stateService = new StateService();

        Stack<String> states = stateService.getAllStates();

        for (String state : states) {
            Team team = scoreboard.getTeam(state);
            if (team == null) {
                team = scoreboard.registerNewTeam(state);
                team.setPrefix(state + " ");
                team.setColor(ChatColor.GRAY);
            }

            for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                String onlinePlayerState = stateService.getState(onlinePlayer);
                if(onlinePlayerState != null && onlinePlayerState.equals(state)) team.addEntry(onlinePlayer.getName());
            }
        }

        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String onlinePlayerState = stateService.getState(onlinePlayer);
            if(onlinePlayerState != null) continue;

            Team team = scoreboard.getTeam("default");
            if(team == null) {
                team = scoreboard.registerNewTeam("default");
                team.setColor(ChatColor.GRAY);
            }
            team.setColor(ChatColor.GRAY);
            team.addEntry(onlinePlayer.getName());
        }
    }
}
