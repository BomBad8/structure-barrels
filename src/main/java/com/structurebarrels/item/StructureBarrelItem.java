package com.structurebarrels.item;

import com.structurebarrels.component.StructureBarrelComponent;
import com.structurebarrels.loot.StructureBarrelLoot;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StructureBarrelItem extends Item {

    public StructureBarrelItem(Properties properties) {
        super(properties);
    }

    public static final Item STRUCTURE_TREASURE_BARREL = register();

    private static Item register() {
        var key = net.minecraft.resources.ResourceKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                net.minecraft.resources.Identifier.fromNamespaceAndPath(
                        "structurebarrels",
                        "structure_treasure_barrel"
                )
        );

        return net.minecraft.core.Registry.register(
                net.minecraft.core.registries.BuiltInRegistries.ITEM,
                key,
                new StructureBarrelItem(
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

    @Override
    public Component getName(ItemStack stack) {
        String structure = stack.get(
                StructureBarrelComponent.STRUCTURE
        );

        if (structure == null) {
            return Component.literal("Structure Treasure Barrel");
        }

        return Component.literal(
                StructureBarrelLoot.displayName(structure)
                        + " Treasure Barrel"
        );
    }

    public InteractionResult useOn(BlockPlaceContext context) {
        Level level = context.getLevel();

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        Player player = context.getPlayer();

        if (player == null) {
            return InteractionResult.FAIL;
        }

        BlockPos pos = context.getClickedPos();

        if (!level.getBlockState(pos).canBeReplaced(context)) {
            return InteractionResult.FAIL;
        }

        BlockState barrelState =
                Blocks.BARREL.getStateForPlacement(context);

        if (barrelState == null) {
            return InteractionResult.FAIL;
        }

        if (!level.setBlock(pos, barrelState, 3)) {
            return InteractionResult.FAIL;
        }

        if (level.getBlockEntity(pos) instanceof BarrelBlockEntity barrel) {

            String structure = context.getItemInHand().get(
                    StructureBarrelComponent.STRUCTURE
            );

            if (structure == null) {
                level.removeBlock(pos, false);
                return InteractionResult.FAIL;
            }

            StructureBarrelLoot.setLootTable(
                    barrel,
                    structure
            );

            barrel.setChanged();
        }

        ItemStack stack = context.getItemInHand();

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}