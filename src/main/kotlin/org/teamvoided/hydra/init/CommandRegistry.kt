package org.teamvoided.hydra.init

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.util.Identifier
import org.teamvoided.hydra.Hydra
import org.teamvoided.hydra.commands.ActionArgumentType
import org.teamvoided.hydra.commands.HydraCommand

object CommandRegistry {

    fun init() {
        ArgumentTypeRegistry.registerArgumentType(
            Identifier(Hydra.MODID, "action"), ActionArgumentType::class.java,
            ActionArgumentType.Info()
        )

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, context, _ ->
            dispatcher.register(CommandManager.literal("hydra").requires { it.hasPermissionLevel(2) }
                .then(CommandManager.literal("test")
                    .then(CommandManager.argument("action", ActionArgumentType(context))
                        .executes { HydraCommand.execute(it) }
                    )
                )
            )
        })
    }
}