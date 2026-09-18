package com.structurebarrels.geyser;

import com.structurebarrels.StructureBarrels;
import com.structurebarrels.item.StructureBarrelItem;
import net.minecraft.core.registries.BuiltInRegistries;
import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.item.custom.v2.CustomItemBedrockOptions;
import org.geysermc.geyser.api.item.custom.v2.NonVanillaCustomItemDefinition;
import org.geysermc.geyser.api.item.custom.v2.component.geyser.GeyserBlockPlacer;
import org.geysermc.geyser.api.item.custom.v2.component.geyser.GeyserItemDataComponents;
import org.geysermc.geyser.api.util.CreativeCategory;
import org.geysermc.geyser.api.util.Identifier;

public final class GeyserIntegration {

    @Subscribe
    public void onDefineCustomItems(
            GeyserDefineCustomItemsEvent event
    ) {
        int javaId = BuiltInRegistries.ITEM.getId(
                StructureBarrelItem.STRUCTURE_TREASURE_BARREL
        );

        event.register(
                NonVanillaCustomItemDefinition.builder(
                        Identifier.of(
                                "structurebarrels:structure_treasure_barrel"
                        ),
                        javaId
                )
                        .displayName("Structure Treasure Barrel")
                        .bedrockOptions(
                                CustomItemBedrockOptions.builder()
                                        .icon("structure_treasure_barrel")
                                        .creativeCategory(CreativeCategory.ITEMS)
                        )
                        .component(
                                GeyserItemDataComponents.BLOCK_PLACER,
                                GeyserBlockPlacer.builder()
                                        .block(Identifier.of("barrel"))
                                        .build()
                        )
                        .build()
        );

        StructureBarrels.LOGGER.info(
                "Registered Structure Treasure Barrel with Geyser (Java ID: {})",
                javaId
        );
    }
}