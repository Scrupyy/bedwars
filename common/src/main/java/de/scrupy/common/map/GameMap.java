package de.scrupy.common.map;

import java.util.List;

public interface GameMap {
    String getName();
    String getBuilderName();
    int getTeamPlayers();
    void setName(String name);
    void setBuilderName(String builderName);
    void setTeamPlayers(int teamPlayers);
    List<GameMapLocation> getGoldSpawner();
    List<GameMapLocation> getIronSpawner();
    List<GameMapLocation> getBronzeSpawner();
    java.util.Map<String, GameMapLocation> getTeamSpawns();
}
