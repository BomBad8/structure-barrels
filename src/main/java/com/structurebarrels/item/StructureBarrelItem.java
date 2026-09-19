package com.structurebarrels.item;

import com.structurebarrels.loot.StructureBarrelLoot;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;

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
        var key = net.minecraft.resources.ResourceKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                net.minecraft.resources.Identifier.fromNamespaceAndPath(
                        "structurebarrels",
                        itemId
                )
        );

        return net.minecraft.core.Registry.register(
                net.minecraft.core.registries.BuiltInRegistries.ITEM,
                key,
                new StructureBarrelItem(
                        structure,
                        new Item.Properties()
                                .setId(key)
                                .stacksTo(64)
                                .component(
                                        DataComponents.ENCHANTMENT_GLINT_OVERRIDE,
                                        true
                                )
                )
        );
    }

    public static void initialize() {
    }

    public Component getName(ItemStack stack) {
        return Component.literal(
                StructureBarrelLoot.displayName(structure)
                        + " Treasure Barrel"
        );
    }

    public String getStructure() {
        return structure;
    }

    public InteractionResult useOn(BlockPlaceContext context) {
        InteractionResult result = super.useOn(context);

        if (result != InteractionResult.FAIL) {
            Level level = context.getLevel();

            if (!level.isClientSide()) {
                BlockPos pos = context.getClickedPos();

                if (level.getBlockEntity(pos) instanceof BarrelBlockEntity barrel) {
                    StructureBarrelLoot.setLootTable(
                            barrel,
                            structure
                    );

                    barrel.setChanged();
                }
            }
        }

        return result;
    }
}