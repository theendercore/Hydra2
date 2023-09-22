package org.teamvoided.hydra.init

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import org.teamvoided.hydra.StreamAction
import org.teamvoided.hydra.Hydra

object StreamActionRegistry {
    val STREAM_ACTION_KEY = RegistryKey.ofRegistry<StreamAction>(Identifier(Hydra.MODID, "stream_action"))
    val STREAM_ACTION_REGISTRY: Registry<StreamAction> =
        FabricRegistryBuilder.createSimple(STREAM_ACTION_KEY).buildAndRegister()

    val SPAWN_MOB = register("spawn_mob", StreamAction {
        EntityType.PIG.spawn(it.world as ServerWorld?, it.blockPos, SpawnReason.EVENT)
    })

    fun init() {
    }

    private fun register(name: String, action: StreamAction): StreamAction {
        return Registry.register(STREAM_ACTION_REGISTRY, Identifier(Hydra.MODID, name), action)
    }
}
