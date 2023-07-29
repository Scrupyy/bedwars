package de.scrupy.setup.util;

import org.bukkit.block.BlockFace;

public class BlockFaceUtil {
    public static float blockFaceToYaw(BlockFace blockFace) {
        return switch (blockFace) {
            case NORTH -> 180.0f;
            case NORTH_EAST -> 225.0f;
            case EAST -> 270.0f;
            case SOUTH_EAST -> 315.0f;
            case SOUTH -> 0.0f;
            case SOUTH_WEST -> 45.0f;
            case WEST -> 90.0f;
            case NORTH_WEST -> 135.0f;
            default -> 0.0f;
        };
    }
}
