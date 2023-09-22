package org.teamvoided.template.commands

import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import org.teamvoided.template.commands.ActionArgumentType.Companion.getActionArgument

object HydraCommand {
    fun execute(context: CommandContext<ServerCommandSource>): Int {
        val argument = getActionArgument(context, "test")
        argument!!.getValue().execute(context.source.player!!)
        return 1
    }
}
