package org.teamvoided.template.init

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import org.teamvoided.template.StreamAction
import org.teamvoided.template.Template

object StreamActionRegistry {
    private val STREAM_ACTION_KEY = RegistryKey.ofRegistry<StreamAction>(Identifier(Template.MODID, "stream_action"))
    private val STREAM_ACTION_REGISTRY: Registry<StreamAction> =
        FabricRegistryBuilder.createSimple(STREAM_ACTION_KEY).buildAndRegister()
    val TEST_ACTION = register("test_action", StreamAction("test_action"))
    fun init() {
    }

    private fun register(name: String, action: StreamAction): StreamAction {
        return Registry.register(STREAM_ACTION_REGISTRY, Identifier(Template.MODID, name), action)
    }
}
