package org.teamvoided.hydra.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.teamvoided.hydra.Hydra
import org.teamvoided.voidlib.config.KotlinXVoidFig
import org.teamvoided.voidlib.config.Side
import org.teamvoided.voidlib.config.VoidFig
import org.teamvoided.voidlib.core.ARGB

@Serializable
data class ClientConfig(
    var username: String,
    var oauthKey: String,
    var prefix: String,
    var channelChatColor: ARGB?,
    var timeFormatting: String,
    var extras: Boolean,
    var autoStart: Boolean,
    var broadcasterId: String
) {
    object VFig: KotlinXVoidFig<ClientConfig>(
        Hydra.id("config"),
        Side.CLIENT,
        ClientConfig("", "", "", ARGB.WHITE, "", extras = false, autoStart = true, broadcasterId = ""),
        Json { prettyPrint = true },
        serializer(),
        "json"
    ) {
        override fun matches(other: VoidFig): Boolean {
            if (other !is VFig) return false
            val otherConfig: ClientConfig = other.getConfig()


            return matchesOther(otherConfig)
        }

        override fun matchesRawData(other: String): Boolean {
            val otherConfig: ClientConfig = deserialize(other)

            return matchesOther(otherConfig)
        }

        private fun matchesOther(otherConfig: ClientConfig): Boolean {
            return config.username == otherConfig.username
                    && config.oauthKey == otherConfig.oauthKey
                    && config.prefix == otherConfig.prefix
                    && config.channelChatColor == otherConfig.channelChatColor
                    && config.timeFormatting == otherConfig.timeFormatting
                    && config.extras == otherConfig.extras
                    && config.autoStart == otherConfig.autoStart
                    && config.broadcasterId == otherConfig.broadcasterId
        }
    }

}