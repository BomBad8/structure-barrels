package com.structurebarrels.loot;

import com.structurebarrels.StructureBarrels;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.storage.loot.LootTable;

public final class StructureBarrelLoot {

    private StructureBarrelLoot() {
    }

    public static void initialize() {
    }

    public static String displayName(String structure) {
        return switch (structure) {
            case "ancient_city" -> "Ancient City";
            case "bastion" -> "Bastion Remnant";
            case "buried_treasure" -> "Buried Treasure";
            case "desert_pyramid" -> "Desert Pyramid";
            case "end_city" -> "End City";
            case "end_ship" -> "End Ship";
            case "jungle_temple" -> "Jungle Temple";
            case "nether_fortress" -> "Nether Fortress";
            case "ocean_monument" -> "Ocean Monument";
            case "pillager_outpost" -> "Pillager Outpost";
            case "stronghold" -> "Stronghold";
            case "trial_chamber_normal" -> "Trial Chamber";
            case "trial_chamber_ominous" -> "Ominous Trial Chamber";
            case "woodland_mansion" -> "Woodland Mansion";
            default -> "Structure";
        };
    }

    private static String lootTableId(String structure) {
        return switch (structure) {
            case "ancient_city" ->
                    "minecraft:chests/ancient_city";

            case "bastion" ->
                    "minecraft:chests/bastion_treasure";

            case "buried_treasure" ->
                    "minecraft:chests/buried_treasure";

            case "desert_pyramid" ->
                    "minecraft:chests/desert_pyramid";

            case "end_city" ->
                    "minecraft:chests/end_city_treasure";

            case "end_ship" ->
                    "minecraft:chests/end_city_treasure";

            case "jungle_temple" ->
                    "minecraft:chests/jungle_temple";

            case "nether_fortress" ->
                    "minecraft:chests/nether_bridge";

            case "ocean_monument" ->
                    "structurebarrels:ocean_monument";

            case "pillager_outpost" ->
                    "structurebarrels:pillager_outpost";

            case "stronghold" ->
                    "minecraft:chests/stronghold_corridor";

            case "trial_chamber_normal" ->
                    "minecraft:chests/trial_chambers/reward";

            case "trial_chamber_ominous" ->
                    "minecraft:chests/trial_chambers/reward_ominous";

            case "woodland_mansion" ->
                    "minecraft:chests/woodland_mansion";

            default -> null;
        };
    }

    public static void generateLoot(
            ServerLevel level,
            BarrelBlockEntity barrel,
            String structure
    ) {
        String lootId = lootTableId(structure);

        if (lootId == null) {
            StructureBarrels.LOGGER.warn(
                    "No loot table found for structure: {}",
                    structure
            );
            return;
        }

        String command =
                "loot insert "
                        + barrel.getBlockPos().getX()
                        + " "
                        + barrel.getBlockPos().getY()
                        + " "
                        + barrel.getBlockPos().getZ()
                        + " loot "
                        + lootId;

        StructureBarrels.LOGGER.info(
                "Running loot command: /{}",
                command
        );

        level.getServer()
                .getCommands()
                .performPrefixedCommand(
                        level.getServer()
                                .createCommandSourceStack()
                                .withLevel(level)
                                .withPosition(
                                        net.minecraft.world.phys.Vec3.atCenterOf(
                                                barrel.getBlockPos()
                                        )
                                )
                                .withSuppressedOutput(),
                        command
                );
    }

    private static ResourceKey<LootTable> vanilla(String path) {
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.withDefaultNamespace(path)
        );
    }

    private static ResourceKey<LootTable> custom(String path) {
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(
                        StructureBarrels.MOD_ID,
                        path
                )
        );
    }
}