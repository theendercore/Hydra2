package org.teamvoided.template.commands

import com.google.gson.JsonObject
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.command.CommandBuildContext
import net.minecraft.command.argument.ArgumentTypeInfo
import net.minecraft.network.PacketByteBuf
import net.minecraft.registry.HolderLookup
import org.teamvoided.template.StreamAction
import org.teamvoided.template.commands.ActionStringReader.Companion.getSuggestions
import org.teamvoided.template.commands.ActionStringReader.Companion.parseForAction
import org.teamvoided.template.init.StreamActionRegistry.STREAM_ACTION_KEY
import java.util.concurrent.CompletableFuture

class ActionArgumentType(context: CommandBuildContext) : ArgumentType<ActionArgument> {
    private val actionLookup: HolderLookup<StreamAction>

    init {
        actionLookup = context.holderLookup(STREAM_ACTION_KEY)
    }

    @Throws(CommandSyntaxException::class)
    override fun parse(reader: StringReader): ActionArgument? {
        return parseForAction(actionLookup, reader)?.let { ActionArgument(it) }
    }

    override fun <S> listSuggestions(
        context: CommandContext<S>,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions?> {
        return getSuggestions(actionLookup, builder)
    }

    companion object {
        fun <S> getActionArgument(context: CommandContext<S>, name: String): ActionArgument? {
            return context.getArgument(name, ActionArgument::class.java);
        }
    }

    class Info : ArgumentTypeInfo<ActionArgumentType, Template> {
        override fun serializeToNetwork(template: Template?, buf: PacketByteBuf) {}
        override fun deserializeFromNetwork(buf: PacketByteBuf): Template {
            return Template()
        }

        override fun serializeToJson(template: Template?, jsonObject: JsonObject) {}
        override fun unpack(type: ActionArgumentType?): Template {
            return Template()
        }
    }

    class Template : ArgumentTypeInfo.Template<ActionArgumentType> {
        override fun instantiate(context: CommandBuildContext): ActionArgumentType {
            return ActionArgumentType(context)
        }

        override fun type(): ArgumentTypeInfo<ActionArgumentType, Template> {
            return Info()
        }
    }
}
