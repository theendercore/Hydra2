package org.teamvoided.template

import net.minecraft.server.network.ServerPlayerEntity
import org.teamvoided.template.init.StreamActionRegistry.STREAM_ACTION_REGISTRY
import java.util.function.Consumer

class StreamAction(private val action: Consumer<ServerPlayerEntity>) {
    fun execute(player: ServerPlayerEntity) {
        action.accept(player)
    }
}
