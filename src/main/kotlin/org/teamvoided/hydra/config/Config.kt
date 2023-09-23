package org.teamvoided.hydra.config

import org.teamvoided.voidlib.core.ARGB

object Config {
    val username: String get() = ClientConfig.VFig.getConfig().username
    val oauthKey: String get() = ClientConfig.VFig.getConfig().oauthKey
    val prefix: String get() = ClientConfig.VFig.getConfig().prefix
    val channelChatColor: ARGB? get() = ClientConfig.VFig.getConfig().channelChatColor
    val timeFormatting: String get() = ClientConfig.VFig.getConfig().timeFormatting
    val extras: Boolean get() = ClientConfig.VFig.getConfig().extras
    val autoStart: Boolean get() = ClientConfig.VFig.getConfig().autoStart
    val broadcasterId: String get() = ClientConfig.VFig.getConfig().broadcasterId
}