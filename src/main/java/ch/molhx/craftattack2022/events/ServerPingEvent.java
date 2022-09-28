package ch.molhx.craftattack2022.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingEvent implements Listener {
    @EventHandler
    public void onServerPing(ServerListPingEvent event) {
        // TODO: Get from config
        String[] motds = {};


        event.setMotd(motds[(int) (Math.random() * motds.length)]);
        event.setMaxPlayers(Math.max(Bukkit.getOnlinePlayers().size(), 1));
    }
}
