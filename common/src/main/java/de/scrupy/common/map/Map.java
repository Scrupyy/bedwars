package de.scrupy.common.map;

import java.util.List;

public interface Map {
    String getName();
    String getBuilderName();
    List<GameMapLocation> getGoldSpawner();
    List<GameMapLocation> getIronSpawner();
    List<GameMapLocation> getBronzeSpawner();
    java.util.Map<String, GameMapLocation> getTeamSpawns();
}
