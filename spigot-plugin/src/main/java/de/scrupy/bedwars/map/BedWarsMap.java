package de.scrupy.bedwars.map;

import de.scrupy.common.map.GameMap;
import de.scrupy.common.map.GameMapLocation;

import java.util.*;

public class BedWarsMap implements GameMap {
    private String name;
    private String builderName;
    private int teamPlayers;
    private final List<GameMapLocation> goldSpawner;
    private final List<GameMapLocation> ironSpawner;
    private final List<GameMapLocation> bronzeSpawner;
    private final Map<String, GameMapLocation> teamSpawns;

    public BedWarsMap() {
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
        return Collections.unmodifiableList(goldSpawner);
    }

    @Override
    public List<GameMapLocation> getIronSpawner() {
        return Collections.unmodifiableList(ironSpawner);
    }

    @Override
    public List<GameMapLocation> getBronzeSpawner() {
        return Collections.unmodifiableList(bronzeSpawner);
    }

    @Override
    public Map<String, GameMapLocation> getTeamSpawns() {
        return Collections.unmodifiableMap(teamSpawns);
    }
}
