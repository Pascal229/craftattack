package ch.molhx.craftattack2022.service;

import ch.molhx.craftattack2022.Craftattack2022;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class StateService {

    private File file = new File("plugins/Craftattack2022/roles.yml");
    private YamlConfiguration rolesFile = YamlConfiguration.loadConfiguration(file);

    public String getState(Player player) {
        Object state = rolesFile.get(player.getUniqueId().toString());

        if(state == null) {
            return "";
        }

        return state.toString();
    }

    public void setState(Player player, String state) {
        String uuid = player.getUniqueId().toString();
        rolesFile.set(uuid, state);
        this.save();
    }


    private void save() {
        try {
            rolesFile.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}