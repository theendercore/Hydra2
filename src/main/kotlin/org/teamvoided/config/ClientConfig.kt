package org.teamvoided.config

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.teamvoided.voidlib.core.ARGB

@Serializable
data class ClientConfig(
    var username: String,
    var oauthKey: String,
    var prefix: String,
    //var channelChatColor: @Contextual ARGB?,
    var timeFormatting: String,
    var extras: Boolean,
    var autoStart: Boolean,
    var broadcasterId: String
)