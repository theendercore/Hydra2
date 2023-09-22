package org.teamvoided.template.init

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import org.teamvoided.template.Template
import java.util.function.Consumer

object StreamActionRegistry {
    private val STREAM_ACTION_KEY = RegistryKey.ofRegistry<StreamAction>(Identifier(Template.MODID, "stream_action"))
    private val STREAM_ACTION_REGISTRY: Registry<StreamAction> =
        FabricRegistryBuilder.createSimple(STREAM_ACTION_KEY).buildAndRegister()

    val SPAWN_MOB = register("spawn_mob", StreamAction {
        EntityType.PIG.spawn(it.world as ServerWorld?, it.blockPos, SpawnReason.EVENT)
    })

    fun init() {
    }

    private fun register(name: String, action: StreamAction): StreamAction {
        return Registry.register(STREAM_ACTION_REGISTRY, Identifier(Template.MODID, name), action)
    }

    @JvmRecord
    data class StreamAction(val action: Consumer<ServerPlayerEntity>)
}
