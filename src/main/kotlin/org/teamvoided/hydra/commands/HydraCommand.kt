package org.teamvoided.hydra.commands

import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import org.teamvoided.hydra.commands.ActionArgumentType.Companion.getActionArgument

object HydraCommand {
    fun execute(context: CommandContext<ServerCommandSource>): Int {
        val argument = getActionArgument(context, "action")
        argument!!.getValue().execute(context.source.player!!)
        return 1
    }
}
