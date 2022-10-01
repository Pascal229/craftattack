package ch.molhx.craftattack2022.service;


import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Set;

public class MotdService {
    private ConfigService configService;

    public MotdService() {
        this.configService = new ConfigService();
    }

    @Nullable
    public ArrayList<String> getAllMotds() {
        Object motds = this.configService.get("motds");
        if(motds == null) return null;

        return (ArrayList<String>) motds;
    }

    public String getRandomMotd() {
        ArrayList<String> allMotds = this.getAllMotds();
        if(allMotds == null) return "NO MOTD FOUND IN CONFIG";
        int randomIndex = (int) (Math.random() * allMotds.size());
        int currentIndex = 0;
        for(String motd : allMotds) {
            if(currentIndex == randomIndex) return motd;
            currentIndex++;
        }
        return null;
    }
}
