package com.structurebarrels;

import com.structurebarrels.component.StructureBarrelComponent;
import com.structurebarrels.item.StructureBarrelItem;
import com.structurebarrels.loot.StructureBarrelLoot;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StructureBarrels implements ModInitializer {

    public static final String MOD_ID = "structurebarrels";

    public static final Logger LOGGER =
            LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        StructureBarrelComponent.initialize();
        StructureBarrelItem.initialize();
        StructureBarrelLoot.initialize();

        LOGGER.info("Structure Treasure Barrels loaded.");
    }
}