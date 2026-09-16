package com.structurebarrels.geyser;

import com.structurebarrels.StructureBarrels;
import com.structurebarrels.item.StructureBarrelItem;
import net.minecraft.core.registries.BuiltInRegistries;
import org.geysermc.event.Subscribe;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomItemsEvent;
import org.geysermc.geyser.api.item.custom.NonVanillaCustomItemDefinition;
import org.geysermc.geyser.api.util.Identifier;

public final class GeyserIntegration {

    private GeyserIntegration() {
    }

    @Subscribe
    public static void onDefineCustomItems(
            GeyserDefineCustomItemsEvent event
    ) {
        int javaId = BuiltInRegistries.ITEM.getId(
                StructureBarrelItem.STRUCTURE_TREASURE_BARREL
        );

        event.register(
                NonVanillaCustomItemDefinition.builder(
                        Identifier.of(
                                StructureBarrels.MOD_ID
                                        + ":structure_treasure_barrel"
                        ),
                        javaId
                )
                .build()
        );
    }
}