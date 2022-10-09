package ch.molhx.craftattack2022.events;

import ch.molhx.craftattack2022.service.MotdService;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingEvent implements Listener {
    @EventHandler
    public void onServerPing(ServerListPingEvent event) {

        MotdService motdService = new MotdService();
        String motd = motdService.getRandomMotd();

        event.setMotd(motd);
        event.setMaxPlayers(Bukkit.getOnlinePlayers().size() + 1);
    }
}
