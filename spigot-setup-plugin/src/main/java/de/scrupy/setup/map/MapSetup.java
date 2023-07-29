package de.scrupy.setup.map;

import com.sk89q.worldedit.regions.CuboidRegion;
import de.scrupy.common.map.GameMap;
import de.scrupy.common.map.GameMapLocation;
import de.scrupy.setup.util.BlockFaceUtil;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;

public class MapSetup {
    private final CuboidRegion cuboidRegion;
    private final World world;
    private final GameMap gameMap;

    public MapSetup(CuboidRegion cuboidRegion, World world) {
        this.cuboidRegion = cuboidRegion;
        this.world = world;
        this.gameMap = new GameMapBuilder();
        setupMap();
    }

    private void setupMap() {
        cuboidRegion.iterator().forEachRemaining(blockVector -> {
            Block block = this.world.getBlockAt(blockVector.getX(), blockVector.getY(), blockVector.getZ());
            locateMaterialSpawner(block);
            locatePlayerTeamSpawn(block);
        });
    }

    private void locatePlayerTeamSpawn(Block block) {
        if (block.getType() == Material.BEDROCK) {
            if (checkBlockAbove(block, Material.PLAYER_HEAD)) {
                Block playerHead = block.getLocation().add(0.0, 1.0, 0.0).getBlock();
                Material teamWool = block.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType();
                float yaw = getYawFromPlayerHead(playerHead);
                gameMap.getTeamSpawns().put(teamWool.name(), createGameMapLocation(block, yaw, 0));
            }
        }
    }

    private float getYawFromPlayerHead(Block block) {
        Skull skull = (Skull) block.getState();
        BlockFace blockFace = skull.getRotation();
        return BlockFaceUtil.blockFaceToYaw(blockFace) + 180f % 360f;
    }

    private void locateMaterialSpawner(Block block) {
        if (block.getType() == Material.GOLD_BLOCK || block.getType() == Material.IRON_BLOCK || block.getType() == Material.TERRACOTTA) {
            if (checkBlockAbove(block, Material.SPAWNER)) {
                addMaterialSpawnerToMap(block);
            }
        }
    }

    private void addMaterialSpawnerToMap(Block block) {
        GameMapLocation gameMapLocation = createGameMapLocation(block);
        switch (block.getType()) {
            case GOLD_BLOCK -> gameMap.getGoldSpawner().add(gameMapLocation);
            case IRON_BLOCK -> gameMap.getIronSpawner().add(gameMapLocation);
            case TERRACOTTA -> gameMap.getBronzeSpawner().add(gameMapLocation);
        }
    }

    private boolean checkBlockAbove(Block block, Material materialAbove) {
        return block.getLocation().add(0.0, 1.0, 0.0).getBlock().getType() == materialAbove;
    }

    private GameMapLocation createGameMapLocation(Block block, float yaw, float pitch) {
        return new GameMapLocation(this.world.getName(), block.getX() + 0.5, block.getY(), block.getX() + 0.5, yaw, pitch);
    }

    private GameMapLocation createGameMapLocation(Block block) {
        return new GameMapLocation(this.world.getName(), block.getX() + 0.5, block.getY(), block.getX() + 0.5);
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
