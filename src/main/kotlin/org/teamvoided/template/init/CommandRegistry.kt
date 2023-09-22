package org.teamvoided.template.init

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import org.teamvoided.template.commands.ActionArgumentType
import org.teamvoided.template.commands.HydraCommand

object CommandRegistry {

    fun init() {
//        ArgumentTypeRegistry.registerArgumentType(Identifier(Template.MODID, "action"), ActionArgumentType::class.java, RegistryKeyOrTagKeyResult.create(StreamActionRegistry.STREAM_ACTION_KEY))

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, context, _ ->
            dispatcher.register(CommandManager.literal("hydra").requires{it.hasPermissionLevel(2)}
                .then(
                    CommandManager.argument("test", ActionArgumentType(context)).executes { HydraCommand.execute(it) }
                )
            )
        })
    }
}