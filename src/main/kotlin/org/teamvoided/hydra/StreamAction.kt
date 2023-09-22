package org.teamvoided.hydra

import net.minecraft.server.network.ServerPlayerEntity
import java.util.function.Consumer

class StreamAction(private val action: Consumer<ServerPlayerEntity>) {
    fun execute(player: ServerPlayerEntity) {
        action.accept(player)
    }
}
