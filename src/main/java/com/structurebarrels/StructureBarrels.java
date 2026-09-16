package com.structurebarrels;

import com.structurebarrels.component.StructureBarrelComponent;
import com.structurebarrels.geyser.GeyserIntegration;
import com.structurebarrels.item.StructureBarrelItem;
import com.structurebarrels.loot.StructureBarrelLoot;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.geysermc.geyser.api.GeyserApi;
import org.geysermc.geyser.api.extension.EventRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StructureBarrels implements ModInitializer, EventRegistrar {

    public static final String MOD_ID = "structurebarrels";

    public static final Logger LOGGER =
            LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        StructureBarrelComponent.initialize();
        StructureBarrelItem.initialize();
        StructureBarrelLoot.initialize();

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            GeyserApi.api().eventBus().register(
                    this,
                    new GeyserIntegration()
            );
        });

        LOGGER.info("Structure Treasure Barrels loaded.");
    }
}