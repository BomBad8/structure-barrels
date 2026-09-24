package com.structurebarrels.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SeededContainerLoot;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;

public class StructureBarrelItem extends BlockItem {

    private final String structure;

    private StructureBarrelItem(
            String structure,
            Properties properties
    ) {
        super(Blocks.BARREL, properties);
        this.structure = structure;
    }

    public static final Item ANCIENT_CITY = register(
            "ancient_city_treasure_barrel",
            "ancient_city"
    );

    public static final Item BASTION = register(
            "bastion_treasure_barrel",
            "bastion"
    );

    public static final Item BURIED_TREASURE = register(
            "buried_treasure_treasure_barrel",
            "buried_treasure"
    );

    public static final Item DESERT_PYRAMID = register(
            "desert_pyramid_treasure_barrel",
            "desert_pyramid"
    );

    public static final Item END_CITY = register(
            "end_city_treasure_barrel",
            "end_city"
    );

    public static final Item END_SHIP = register(
            "end_ship_treasure_barrel",
            "end_ship"
    );

    public static final Item JUNGLE_TEMPLE = register(
            "jungle_temple_treasure_barrel",
            "jungle_temple"
    );

    public static final Item NETHER_FORTRESS = register(
            "nether_fortress_treasure_barrel",
            "nether_fortress"
    );

    public static final Item OCEAN_MONUMENT = register(
            "ocean_monument_treasure_barrel",
            "ocean_monument"
    );

    public static final Item PILLAGER_OUTPOST = register(
            "pillager_outpost_treasure_barrel",
            "pillager_outpost"
    );

    public static final Item STRONGHOLD = register(
            "stronghold_treasure_barrel",
            "stronghold"
    );

    public static final Item TRIAL_CHAMBER_NORMAL = register(
            "trial_chamber_normal_treasure_barrel",
            "trial_chamber_normal"
    );

    public static final Item TRIAL_CHAMBER_OMINOUS = register(
            "trial_chamber_ominous_treasure_barrel",
            "trial_chamber_ominous"
    );

    public static final Item WOODLAND_MANSION = register(
            "woodland_mansion_treasure_barrel",
            "woodland_mansion"
    );

    private static Item register(
            String itemId,
            String structure
    ) {
        ResourceKey<Item> key = ResourceKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                Identifier.fromNamespaceAndPath(
                        "structurebarrels",
                        itemId
                )
        );

        ResourceKey<LootTable> lootTable = ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.parse(
                        StructureBarrelLootId.get(structure)
                )
        );

        return net.minecraft.core.Registry.register(
                BuiltInRegistries.ITEM,
                key,
                new StructureBarrelItem(
                        structure,
                        new Item.Properties()
                                .setId(key)
                                .stacksTo(64)
                                .component(
                                        net.minecraft.core.component.DataComponents.ENCHANTMENT_GLINT_OVERRIDE,
                                        true
                                )
                                .component(
                                        net.minecraft.core.component.DataComponents.CONTAINER_LOOT,
                                        new SeededContainerLoot(
                                                lootTable,
                                                0L
                                        )
                                )
                )
        );
    }

    public static void initialize() {
    }

    public net.minecraft.network.chat.Component getName(ItemStack stack) {
        return net.minecraft.network.chat.Component.literal(
                displayName(structure) + " Treasure Barrel"
        );
    }

    private static String displayName(String structure) {
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

    public String getStructure() {
        return structure;
    }

    public InteractionResult useOn(BlockPlaceContext context) {
        return super.useOn(context);
    }

    private static final class StructureBarrelLoot {
        private StructureBarrelLoot() {
        }
    }

    private static final class StructureBarrelLootId {

        private static String get(String structure) {
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

                default ->
                        "minecraft:empty";
            };
        }
    }
}