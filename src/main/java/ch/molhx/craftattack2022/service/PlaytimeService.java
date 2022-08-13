package ch.molhx.craftattack2022.service;

import ch.molhx.craftattack2022.Craftattack2022;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;

public class PlaytimeService {
    private File file = new File("plugins/Craftattack2022/playtime.yml");
    private YamlConfiguration rolesFile = YamlConfiguration.loadConfiguration(file);
    private BukkitTask runnable;

    public void start() {
        //LOOP THROUGH ALL PLAYERS
        this.runnable = new BukkitRunnable() {
            @Override
            public void run() {
                TablistService tablistService = new TablistService();

                for(Player player : Bukkit.getOnlinePlayers()) {
                    String uuid = player.getUniqueId().toString();
                    if(!rolesFile.contains(uuid)) {
                        rolesFile.set(uuid, 0);
                    }
                    int playtime = rolesFile.getInt(uuid);
                    rolesFile.set(uuid, playtime + 1);
                }
                save();

                for(Player player : Bukkit.getOnlinePlayers()) {
                    tablistService.updateTablist(player);
                }
            }
        }.runTaskTimer(Craftattack2022.getInstance(), 0, 20);
    }

    public void stop() {
        this.runnable.cancel();
    }

    public int getPlaytime(Player player) {
        String uuid = player.getUniqueId().toString();
        if(!rolesFile.contains(uuid)) {
            rolesFile.set(uuid, 0);
        }
        return rolesFile.getInt(uuid);
    }

    public int getPlaytimeByUUID(String uuid) {
        if(!rolesFile.contains(uuid)) {
            rolesFile.set(uuid, 0);
        }
        return rolesFile.getInt(uuid);
    }

    public void setPlaytime(Player player, int playtime) {
        String uuid = player.getUniqueId().toString();
        rolesFile.set(uuid, playtime);
        save();
    }

    public void addPlaytime(Player player, int playtime) {
        String uuid = player.getUniqueId().toString();
        rolesFile.set(uuid, getPlaytime(player) + playtime);
        save();
    }

    public void removePlaytime(Player player, int playtime) {
        String uuid = player.getUniqueId().toString();
        rolesFile.set(uuid, getPlaytime(player) - playtime);
        save();
    }

    public String formatPlaytime(int playtime) {
        int hours = playtime / 3600;
        int minutes = (playtime % 3600) / 60;
        int seconds = (playtime % 3600) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void save() {
        try {
            rolesFile.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
