package com.structurebarrels.component;

import com.mojang.serialization.Codec;
import com.structurebarrels.StructureBarrels;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Registry;

public final class StructureBarrelComponent {

    public static final DataComponentType<String> STRUCTURE = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(
                    StructureBarrels.MOD_ID,
                    "structure"
            ),
            DataComponentType.<String>builder()
                    .persistent(Codec.STRING)
                    .build()
    );

    private StructureBarrelComponent() {
    }

    public static void initialize() {
    }
}