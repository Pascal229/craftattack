package ch.molhx.craftattack2022.service;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TablistService {
    public void setTablist(Player player) {
        player.setPlayerListHeaderFooter("", "");
    }

    public void setAllPlayerTeams() {
        Bukkit.getOnlinePlayers().forEach(this::setPlayerTeams);
    }

    public void setPlayerTeams(Player player) {
        Scoreboard scoreboard = player.getScoreboard();

        StateService stateService = new StateService();

        for (String state : stateService.getAllStates()) {
            Team team = scoreboard.getTeam(state);
            if(team == null) {
                team = scoreboard.registerNewTeam(state);
            }

            team.setPrefix(stateService.getState(player) + " ");
            team.setColor(ChatColor.GRAY);

            System.out.println(stateService.getState(player) + " " + state);
            if(stateService.getState(player).equals(state)) {
                team.addEntry(player.getName());
            }
        }
    }
}
