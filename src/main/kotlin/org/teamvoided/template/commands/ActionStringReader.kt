package org.teamvoided.template.commands

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.command.CommandSource
import net.minecraft.registry.Holder
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.teamvoided.template.StreamAction
import org.teamvoided.template.init.StreamActionRegistry.STREAM_ACTION_KEY
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Function

class ActionStringReader private constructor(
    private val lookup: HolderLookup<StreamAction>,
    private val reader: StringReader
) {
    private var result: Holder<StreamAction>? = null
    private var suggestions: Function<SuggestionsBuilder, CompletableFuture<Suggestions?>>? = null
    @Throws(CommandSyntaxException::class)
    private fun read() {
        val cursor = reader.cursor
        val identifier = Identifier.fromCommandInput(reader)
        val optional: Optional<out Holder<StreamAction>> =
            lookup.getHolder(RegistryKey.of(STREAM_ACTION_KEY, identifier))
        result = optional.orElseThrow<CommandSyntaxException> {
            reader.cursor = cursor
            ID_INVALID_EXCEPTION.createWithContext(reader, identifier)
        }
    }

    @Throws(CommandSyntaxException::class)
    private fun consume() {
        suggestions = Function { builder: SuggestionsBuilder -> suggest(builder) }
        read()
        suggestions = Function { obj: SuggestionsBuilder -> obj.buildFuture() }
    }

    private fun suggest(builder: SuggestionsBuilder): CompletableFuture<Suggestions?> {
        return CommandSource.suggestIdentifiers(
            lookup.streamElementKeys().map { obj: RegistryKey<StreamAction> -> obj.value }, builder
        )
    }

    companion object {
        private val ID_INVALID_EXCEPTION =
            DynamicCommandExceptionType { id: Any? -> Text.translatable("argument.hydra.action.id.invalid", id) }

        @JvmStatic
        @Throws(CommandSyntaxException::class)
        fun parseForAction(lookup: HolderLookup<StreamAction>, reader: StringReader): Holder<StreamAction>? {
            val cursor = reader.cursor
            return try {
                val actionReader = ActionStringReader(lookup, reader)
                actionReader.consume()
                actionReader.result
            } catch (e: CommandSyntaxException) {
                reader.cursor = cursor
                throw e
            }
        }

        @JvmStatic
        fun getSuggestions(
            lookup: HolderLookup<StreamAction>,
            builder: SuggestionsBuilder
        ): CompletableFuture<Suggestions?> {
            val stringReader = StringReader(builder.input)
            stringReader.cursor = builder.start
            val actionReader = ActionStringReader(lookup, stringReader)
            try {
                actionReader.consume()
            } catch (e: CommandSyntaxException) {
            }
            return actionReader.suggestions!!.apply(builder.createOffset(stringReader.cursor))
        }
    }
}
