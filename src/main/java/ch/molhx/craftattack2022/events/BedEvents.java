package ch.molhx.craftattack2022.events;

import ch.molhx.craftattack2022.service.SleepService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BedEvents implements Listener {

    private SleepService sleepService = new SleepService();

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        sleepService.addLayingPlayer(event.getPlayer());
    }

    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent event) {
        sleepService.removeLayingPlayer(event.getPlayer());
    }

}
