package ch.molhx.craftattack2022.events;

import ch.molhx.craftattack2022.service.StateService;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        StateService stateService = new StateService();
        String state = stateService.getState(e.getPlayer());

        if(state == null) {
            state = "";
        } else if(!state.equals("")) {
            state = state + " ";
        }

        e.setFormat(ChatColor.GRAY + state + ChatColor.GRAY + e.getPlayer().getName() + ": " + ChatColor.WHITE + e.getMessage());
    }
}
