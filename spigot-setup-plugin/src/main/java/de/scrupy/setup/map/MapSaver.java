package de.scrupy.setup.map;

import com.google.gson.Gson;
import de.scrupy.common.map.GameMap;
import de.scrupy.setup.BedWarsSetup;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MapSaver {
    private static final String PATH = BedWarsSetup.getInstance().getDataFolder().getPath() + "/maps";

    public MapSaver() {
        createMapOutputFolder();
    }

    public void saveGameMapToFile(GameMap gameMap) throws IOException {
        if (!gameMapExists(gameMap)) {
            Gson gson = new Gson();
            gson.toJson(gameMap, new FileWriter(getFilePath(gameMap)));
        }
    }

    public boolean gameMapExists(GameMap gameMap) {
        return new File(getFilePath(gameMap)).exists();
    }

    public String getFileName(GameMap gameMap) {
        return gameMap.getName() + "_" + getTeamAmount(gameMap) + "x" + gameMap.getTeamPlayers();
    }

    private String getFilePath(GameMap gameMap) {
        return PATH + "/" + getFileName(gameMap);
    }

    private int getTeamAmount(GameMap map) {
        return map.getTeamSpawns().size();
    }

    private void createMapOutputFolder() {
        File mapFolder = new File(PATH);
        if (!mapFolder.exists()) {
            if (!mapFolder.mkdirs()) {
                Bukkit.getLogger().warning("Error while creating maps output folder: " + PATH);
            }
        }
    }
}
