package ch.molhx.craftattack2022.service;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigService {

    private File file = new File("plugins/Craftattack2022/config.yml");
    private YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    public void set(String key, Object value) {
        config.set(key, value);
        this.save();
    }

    public Object get(String key) {
        return config.get(key);
    }

    private void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
