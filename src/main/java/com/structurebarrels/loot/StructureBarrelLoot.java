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

    public static ResourceKey<LootTable> lootTable(String structure) {
        return switch (structure) {
            case "ancient_city" ->
                    vanilla("chests/ancient_city");

            case "bastion" ->
                    vanilla("chests/bastion_treasure");

            case "buried_treasure" ->
                    vanilla("chests/buried_treasure");

            case "desert_pyramid" ->
                    vanilla("chests/desert_pyramid");

            case "end_city" ->
                    vanilla("chests/end_city_treasure");

            case "end_ship" ->
                    vanilla("chests/end_city_treasure");

            case "jungle_temple" ->
                    vanilla("chests/jungle_temple");

            case "nether_fortress" ->
                    vanilla("chests/nether_bridge");

            case "ocean_monument" ->
                    custom("ocean_monument");

            case "pillager_outpost" ->
                    custom("pillager_outpost");

            case "stronghold" ->
                    vanilla("chests/stronghold_corridor");

            case "trial_chamber_normal" ->
                    vanilla("chests/trial_chambers/reward");

            case "trial_chamber_ominous" ->
                    vanilla("chests/trial_chambers/reward_ominous");

            case "woodland_mansion" ->
                    vanilla("chests/woodland_mansion");

            default -> null;
        };
    }

    public static void generateLoot(
            ServerLevel level,
            BarrelBlockEntity barrel,
            String structure
    ) {
        ResourceKey<LootTable> tableKey = lootTable(structure);

        if (tableKey == null) {
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
                        + tableKey.location();

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