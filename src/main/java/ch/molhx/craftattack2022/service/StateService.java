package ch.molhx.craftattack2022.service;

import ch.molhx.craftattack2022.Craftattack2022;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Stack;

public class StateService {

    private File file = new File("plugins/Craftattack2022/states.yml");
    private YamlConfiguration rolesFile = YamlConfiguration.loadConfiguration(file);

    public String getState(Player player) {
        Object state = rolesFile.get(player.getUniqueId().toString());

        if(state == "") {
            return null;
        }

        return this.convertToColor(state.toString());
    }

    public void setState(Player player, String state) {
        String uuid = player.getUniqueId().toString();
        rolesFile.set(uuid, state);
        this.save();
    }

    public Stack<String> getAllStates() {
        Stack<String> allStates = new Stack<String>();
        for(String key : rolesFile.getKeys(false)) {
            allStates.push(this.convertToColor(rolesFile.get(key).toString()));
        }

        return allStates;
    }

    private String convertToColor(String state) {
        return state.replace("&", "§");
    }

    private void save() {
        try {
            rolesFile.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
