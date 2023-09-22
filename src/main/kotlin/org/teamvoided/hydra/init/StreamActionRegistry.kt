package org.teamvoided.hydra.init

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.SpawnReason
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import org.teamvoided.hydra.Hydra
import org.teamvoided.hydra.StreamAction

object StreamActionRegistry {
    val STREAM_ACTION_KEY = RegistryKey.ofRegistry<StreamAction>(Identifier(Hydra.MODID, "stream_action"))
    val STREAM_ACTION_REGISTRY: Registry<StreamAction> =
        FabricRegistryBuilder.createSimple(STREAM_ACTION_KEY).buildAndRegister()

    val SPAWN_MOB = register("spawn_mob", StreamAction {
        EntityType.PIG.spawn(it.serverWorld, it.blockPos, SpawnReason.EVENT)
    })

    val SPAWN_RANDOM_MOB = register("spawn_random_mob", StreamAction {
        fun spawnRandom() {
            Registries.ENTITY_TYPE.getRandom(it.random).ifPresent { it1 ->
                val type = it1.value();
                if (type.spawnGroup != SpawnGroup.MISC) {
                    type.spawn(it.serverWorld, it.blockPos, SpawnReason.EVENT)
                } else {
                    spawnRandom();
                }
            }
        }
        
        spawnRandom()
    })

    fun init() {
    }

    private fun register(name: String, action: StreamAction): StreamAction {
        return Registry.register(STREAM_ACTION_REGISTRY, Identifier(Hydra.MODID, name), action)
    }
}
