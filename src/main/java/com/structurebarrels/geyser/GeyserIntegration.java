package com.structurebarrels.geyser;

import com.structurebarrels.StructureBarrels;
import com.structurebarrels.item.StructureBarrelItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.item.custom.v2.CustomItemBedrockOptions;
import org.geysermc.geyser.api.item.custom.v2.NonVanillaCustomItemDefinition;
import org.geysermc.geyser.api.item.custom.v2.component.geyser.GeyserBlockPlacer;
import org.geysermc.geyser.api.item.custom.v2.component.geyser.GeyserItemDataComponents;
import org.geysermc.geyser.api.item.custom.v2.component.java.JavaItemDataComponents;
import org.geysermc.geyser.api.util.CreativeCategory;
import org.geysermc.geyser.api.util.Identifier;

public final class GeyserIntegration {

    @Subscribe
    public void onDefineCustomItems(
            GeyserDefineCustomItemsEvent event
    ) {
        register(
                event,
                StructureBarrelItem.ANCIENT_CITY,
                "ancient_city_treasure_barrel",
                "Ancient City Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.BASTION,
                "bastion_treasure_barrel",
                "Bastion Remnant Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.BURIED_TREASURE,
                "buried_treasure_treasure_barrel",
                "Buried Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.DESERT_PYRAMID,
                "desert_pyramid_treasure_barrel",
                "Desert Pyramid Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.END_CITY,
                "end_city_treasure_barrel",
                "End City Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.END_SHIP,
                "end_ship_treasure_barrel",
                "End Ship Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.JUNGLE_TEMPLE,
                "jungle_temple_treasure_barrel",
                "Jungle Temple Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.NETHER_FORTRESS,
                "nether_fortress_treasure_barrel",
                "Nether Fortress Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.OCEAN_MONUMENT,
                "ocean_monument_treasure_barrel",
                "Ocean Monument Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.PILLAGER_OUTPOST,
                "pillager_outpost_treasure_barrel",
                "Pillager Outpost Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.STRONGHOLD,
                "stronghold_treasure_barrel",
                "Stronghold Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.TRIAL_CHAMBER_NORMAL,
                "trial_chamber_normal_treasure_barrel",
                "Trial Chamber Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.TRIAL_CHAMBER_OMINOUS,
                "trial_chamber_ominous_treasure_barrel",
                "Ominous Trial Chamber Treasure Barrel"
        );

        register(
                event,
                StructureBarrelItem.WOODLAND_MANSION,
                "woodland_mansion_treasure_barrel",
                "Woodland Mansion Treasure Barrel"
        );
    }

    private static void register(
            GeyserDefineCustomItemsEvent event,
            Item item,
            String itemId,
            String displayName
    ) {
        int javaId = BuiltInRegistries.ITEM.getId(item);

        event.register(
                NonVanillaCustomItemDefinition.builder(
                        Identifier.of(
                                "structurebarrels:" + itemId
                        ),
                        javaId
                )
                        .displayName(displayName)
                        .bedrockOptions(
                                CustomItemBedrockOptions.builder()
                                        .creativeCategory(CreativeCategory.ITEMS)
                        )
                        .component(
                                JavaItemDataComponents.MAX_STACK_SIZE,
                                64
                        )
                        .component(
                                JavaItemDataComponents.ENCHANTMENT_GLINT_OVERRIDE,
                                true
                        )
                        .component(
                                GeyserItemDataComponents.BLOCK_PLACER,
                                GeyserBlockPlacer.builder()
                                        .block(Identifier.of("minecraft:barrel"))
                                        .useBlockIcon(true)
                                        .build()
                        )
                        
                        .build()
        );

        StructureBarrels.LOGGER.info(
                "Registered {} with Geyser (Java ID: {})",
                displayName,
                javaId
        );
    }
}