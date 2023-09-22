package org.teamvoided.template.init

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.util.Identifier
import org.teamvoided.template.Template
import org.teamvoided.template.commands.ActionArgumentType
import org.teamvoided.template.commands.HydraCommand

object CommandRegistry {

    fun init() {
        ArgumentTypeRegistry.registerArgumentType(
            Identifier(Template.MODID, "action"), ActionArgumentType::class.java,
            ActionArgumentType.Info()
        )

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, context, _ ->
            dispatcher.register(CommandManager.literal("hydra").requires { it.hasPermissionLevel(2) }
                .then(
                    CommandManager.argument("test", ActionArgumentType(context)).executes { HydraCommand.execute(it) }
                )
            )
        })
    }
}