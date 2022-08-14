package ch.molhx.craftattack2022.service;

import ch.molhx.craftattack2022.Craftattack2022;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.Stack;

public class SleepService {
    private Stack<String> layingPlayers = new Stack<String>();
    private ConfigService configService = new ConfigService();
    private String prefix = Craftattack2022.getInstance().getPrefix();
    private Boolean isSkipping = false;

    public void addLayingPlayer(Player player) {
        if(this.layingPlayers.contains(player.getUniqueId().toString())) return;
        this.layingPlayers.push(player.getUniqueId().toString());

        this.skipNight(this.getWorld(), player, true);
    }

    public void removeLayingPlayer(Player player) {
        if(!this.layingPlayers.contains(player.getUniqueId().toString())) return;
        this.layingPlayers.remove(player.getUniqueId().toString());

        this.skipNight(this.getWorld(), player, false);
    }

    public void skipNight(World world, Player latestPlayer, boolean isAddition) {
        Collection<Player> onlinePlayers = (Collection<Player>) Bukkit.getOnlinePlayers();

        for(String uuid : this.layingPlayers) {
            Player player = this.getPlayerByUUID(uuid);
            if(player == null) {
                this.layingPlayers.remove(uuid);
                continue;
            };

            // remove all offline players from the list
            if(!onlinePlayers.contains(player)) this.removeLayingPlayer(player);
        }
        if(isAddition) this.addLayingPlayer(latestPlayer);

        if(!this.isNight() && isAddition) {
            this.isSkipping = false;
            Bukkit.broadcastMessage(prefix + ChatColor.GRAY + "The night can be skipped only in the night.");
            return;
        }

        if(this.layingPlayers.size() == 0 && this.isNight()) {
            this.isSkipping = false;
            Bukkit.broadcastMessage(prefix + ChatColor.GRAY + "All players left the bed.");
            return;
        }

        if(isAddition) Bukkit.broadcastMessage(prefix + ChatColor.GRAY +  latestPlayer.getName() + " zzZ");

        if(layingPlayers.size() < this.getMinLayingPlayers()) {
            this.isSkipping = false;
            if(!this.isNight()) return;
            Bukkit.broadcastMessage(prefix + ChatColor.GRAY + this.layingPlayers.size() + " / " + this.getMinLayingPlayers() + " players are laying.");
            return;
        } else {
            if(this.isSkipping) return;
            this.isSkipping = true;

            Bukkit.broadcastMessage(prefix + ChatColor.GRAY + "The night has been skipped. (" + this.layingPlayers.size() + " / " + this.getMinLayingPlayers() + ")");
            world.setTime(0);
            world.setStorm(false);
            world.setThundering(false);
            world.setWeatherDuration(0);
        }
    }

    public World getWorld() {
        return Craftattack2022.getInstance().getServer().getWorlds().get(0);
    }

    public int getMinLayingPlayers() {
        if(this.configService.get("minLayingPlayers") == null) {
            this.configService.set("minLayingPlayers", 50);
        }
        int minLayingPlayersPercentage = (int) this.configService.get("minLayingPlayers");
        return (int) Math.ceil(this.getWorld().getPlayers().size() * (minLayingPlayersPercentage / 100.0));
    }

    private boolean isNight() {
        return this.getWorld().getTime() > 12500;
    }

    private Player getPlayerByUUID(String uuid) {
        for (Player player : this.getWorld().getPlayers()) {
            if (player.getUniqueId().toString().equals(uuid)) return player;
        }
        return null;
    }
}
