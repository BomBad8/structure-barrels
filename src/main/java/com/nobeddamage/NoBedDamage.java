package com.nobeddamage;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;

public class NoBedDamage implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            // Only prevent bed / respawn-anchor explosion damage to players.
            if (entity instanceof ServerPlayer && source.is(DamageTypes.BAD_RESPAWN_POINT)) {
                return false;
            }

            return true;
        });

        System.out.println("[NoBedDamage] Loaded - bed/respawn-anchor damage disabled for players.");
    }
}
