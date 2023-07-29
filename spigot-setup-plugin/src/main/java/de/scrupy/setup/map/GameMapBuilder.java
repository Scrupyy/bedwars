package de.scrupy.setup.map;

import de.scrupy.common.map.Map;
import de.scrupy.common.map.GameMapLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameMapBuilder implements Map {
    private final String name;
    private final String builderName;
    private final List<GameMapLocation> goldSpawner;
    private final List<GameMapLocation> ironSpawner;
    private final List<GameMapLocation> bronzeSpawner;
    private final java.util.Map<String, GameMapLocation> teamSpawns;

    public GameMapBuilder(String name, String builderName) {
        this.name = name;
        this.builderName = builderName;
        this.goldSpawner = new ArrayList<>();
        this.ironSpawner = new ArrayList<>();
        this.bronzeSpawner = new ArrayList<>();
        this.teamSpawns = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBuilderName() {
        return builderName;
    }

    @Override
    public List<GameMapLocation> getGoldSpawner() {
        return goldSpawner;
    }

    @Override
    public List<GameMapLocation> getIronSpawner() {
        return ironSpawner;
    }

    @Override
    public List<GameMapLocation> getBronzeSpawner() {
        return bronzeSpawner;
    }

    @Override
    public java.util.Map<String, GameMapLocation> getTeamSpawns() {
        return teamSpawns;
    }
}
