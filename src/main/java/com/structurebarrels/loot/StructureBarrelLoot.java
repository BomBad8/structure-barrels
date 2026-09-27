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
                    "structurebarrels:ancient_city";

            case "bastion" ->
                    "structurebarrels:bastion_treasure";

            case "buried_treasure" ->
                    "structurebarrels:buried_treasure";

            case "desert_pyramid" ->
                    "structurebarrels:desert_pyramid";

            case "end_city" ->
                    "structurebarrels:end_city_treasure";

            case "jungle_temple" ->
                    "structurebarrels:jungle_temple";

            case "nether_fortress" ->
                    "structurebarrels:nether_bridge";

            case "ocean_monument" ->
                    "structurebarrels:ocean_monument";

            case "pillager_outpost" ->
                    "structurebarrels:pillager_outpost";

            case "stronghold" ->
                    "structurebarrels:stronghold_corridor";

            case "trial_chamber_normal" ->
                    "structurebarrels:trial_chambers_reward";

            case "trial_chamber_ominous" ->
                    "structurebarrels:trial_chambers_reward_ominous";

            case "woodland_mansion" ->
                    "structurebarrels:woodland_mansion";

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