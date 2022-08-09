package ch.molhx.craftattack2022.events;

import ch.molhx.craftattack2022.Craftattack2022;
import ch.molhx.craftattack2022.service.TablistService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import sun.tools.jconsole.Tab;

public class JoinEvent implements Listener {

        @EventHandler
        public void onJoin(PlayerJoinEvent event) {
                Player player = event.getPlayer();
                event.setJoinMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " has joined the game");

                TablistService tablistService = new TablistService();
                tablistService.setTablist(player);
                tablistService.setAllPlayerTeams();
        }

        @EventHandler
        public void onQuit(PlayerQuitEvent event) {
                Player player = event.getPlayer();
                event.setQuitMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " has left the game");
        }
}
