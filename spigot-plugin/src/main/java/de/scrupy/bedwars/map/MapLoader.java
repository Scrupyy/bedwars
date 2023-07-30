package de.scrupy.bedwars.map;

import com.google.gson.Gson;
import de.scrupy.bedwars.BedWars;
import org.bukkit.Bukkit;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MapLoader {
    private static final String MAP_FOLDER_PATH = BedWars.getInstance().getDataFolder() + "/maps";

    public MapLoader() {
        createFolder();
        checkForAvailableMaps();
    }

    @Nullable
    public BedWarsMap loadMap(String name, int teamAmount, int teamPlayers) {
        String fileName = getFileName(name, teamAmount, teamPlayers);
        File mapFile = new File(fileName);
        if (mapFile.exists()) {
            try {
                return loadFromFile(mapFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Bukkit.getLogger().warning("Map with file name " + fileName + " not exists");
        }
        return null;
    }

    private BedWarsMap loadFromFile(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(file), BedWarsMap.class);
    }

    private void checkForAvailableMaps() {
        File mapFolder = getMapFolder();
        if (isEmpty(mapFolder)) {
            Bukkit.getLogger().warning("Currently no maps available to load.");
        }
    }

    private boolean isEmpty(File file) {
        if (file == null || !file.isDirectory()) {
            return false;
        }
        File[] files = file.listFiles();
        return files != null && files.length == 0;
    }

    private void createFolder() {
        File mapFolder = getMapFolder();
        if (!mapFolder.exists()) {
            if (!mapFolder.mkdirs()) {
                Bukkit.getLogger().warning("Error while creating maps folder.");
            }
        }
    }

    private String getFileName(String name, int teamAmount, int teamPlayers) {
        return name + "_" + teamAmount + "x" + teamPlayers;
    }

    private File getMapFolder() {
        return new File(MAP_FOLDER_PATH);
    }
}
