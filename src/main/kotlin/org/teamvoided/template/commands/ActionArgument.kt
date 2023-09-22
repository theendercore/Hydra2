package org.teamvoided.template.commands

import net.minecraft.registry.Holder
import org.teamvoided.template.StreamAction
import java.util.function.Predicate

class ActionArgument(private val actionHolder: Holder<StreamAction>) : Predicate<StreamAction> {
    override fun test(action: StreamAction): Boolean {
        return action === actionHolder.value()
    }

    fun getValue(): StreamAction {
        return actionHolder.value()
    }
}
