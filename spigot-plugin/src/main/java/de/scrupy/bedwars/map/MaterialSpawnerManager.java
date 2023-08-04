package de.scrupy.bedwars.map;

import de.scrupy.bedwars.Game;
import de.scrupy.bedwars.config.GameSettingsConfig;
import de.scrupy.bedwars.util.ItemBuilder;
import de.scrupy.common.map.GameMap;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MaterialSpawnerManager {
    private final GameMap gameMap;
    private final ItemStack bronze, iron, gold;
    private final List<MaterialSpawner> materialSpawners;

    public MaterialSpawnerManager(GameMap gameMap) {
        this.gameMap = gameMap;
        this.bronze = new ItemBuilder(Material.BRICK).setDisplayName("§cBronze").build();
        this.iron = new ItemBuilder(Material.IRON_INGOT).setDisplayName("§fIron").build();
        this.gold = new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§6Gold").build();
        this.materialSpawners = new ArrayList<>();
        if (this.gameMap != null) {
            addSpawner();
        }
    }

    private void addSpawner() {
        MaterialSpawner bronzeSpawner = new MaterialSpawner(
                bronze,
                GameSettingsConfig.getInstance().getInteger("bronzeSpawnerTime"),
                gameMap.getBronzeSpawner()
        );

        MaterialSpawner ironSpawner = new MaterialSpawner(
                iron,
                GameSettingsConfig.getInstance().getInteger("ironSpawnerTime"),
                gameMap.getIronSpawner()
        );

        MaterialSpawner goldSpawner = new MaterialSpawner(
                gold,
                GameSettingsConfig.getInstance().getInteger("goldSpawnerTime"),
                gameMap.getGoldSpawner()
        );

        materialSpawners.add(bronzeSpawner);
        materialSpawners.add(ironSpawner);
        materialSpawners.add(goldSpawner);
    }

    public void startSpawner() {
        for (MaterialSpawner materialSpawner : materialSpawners) {
            materialSpawner.startSpawningMaterials();
        }
    }
}
