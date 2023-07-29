package de.scrupy.setup.map;

import de.scrupy.common.map.GameMap;
import de.scrupy.common.map.GameMapLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameMapBuilder implements GameMap {
    private String name;
    private String builderName;
    private int teamPlayers;
    private final List<GameMapLocation> goldSpawner;
    private final List<GameMapLocation> ironSpawner;
    private final List<GameMapLocation> bronzeSpawner;
    private final java.util.Map<String, GameMapLocation> teamSpawns;

    public GameMapBuilder() {
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
    public int getTeamPlayers() {
        return teamPlayers;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBuilderName(String builderName) {
        this.builderName = builderName;
    }

    @Override
    public void setTeamPlayers(int teamPlayers) {
        this.teamPlayers = teamPlayers;
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
