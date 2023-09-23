package org.teamvoided.config

import kotlinx.serialization.Contextual
import org.teamvoided.voidlib.core.ARGB

object Config {
    val username: String get() = ClientVFig.getConfig().username
    val oauthKey: String get() = ClientVFig.getConfig().oauthKey
    val prefix: String get() = ClientVFig.getConfig().prefix
    //val channelChatColor: @Contextual ARGB? get() = ClientVFig.getConfig().channelChatColor
    val timeFormatting: String get() = ClientVFig.getConfig().timeFormatting
    val extras: Boolean get() = ClientVFig.getConfig().extras
    val autoStart: Boolean get() = ClientVFig.getConfig().autoStart
    val broadcasterId: String get() = ClientVFig.getConfig().broadcasterId
}