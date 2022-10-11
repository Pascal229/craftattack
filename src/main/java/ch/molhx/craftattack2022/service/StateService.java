package ch.molhx.craftattack2022.service;

import ch.molhx.craftattack2022.Craftattack2022;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Stack;

public class StateService {

    private File file = new File("plugins/Craftattack2022/states.yml");
    private YamlConfiguration stateFile = YamlConfiguration.loadConfiguration(file);

    public String getState(Player player) {
        Object state = stateFile.get(player.getUniqueId().toString());

        if(state == "" || state == null) return null;

        return this.convertToColor(state.toString());
    }

    public void setState(Player player, String state) {
        String uuid = player.getUniqueId().toString();
        stateFile.set(uuid, state);
        this.save();
    }

    public Stack<String> getAllStates() {
        Stack<String> allStates = new Stack<String>();
        for(String key : stateFile.getKeys(false)) {
            allStates.push(this.convertToColor(stateFile.get(key).toString()));
        }

        return allStates;
    }

    private String convertToColor(String state) {
        return state.replace("&", "§");
    }

    private void save() {
        try {
            stateFile.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
