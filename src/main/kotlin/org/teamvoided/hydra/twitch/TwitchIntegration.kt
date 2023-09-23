package org.teamvoided.hydra.twitch

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import org.teamvoided.hydra.ModConfig

@Environment(EnvType.CLIENT)
object TwitchIntegration {
    private var innerClient: TwitchClient? = null

    private var enabled = false

    val client: TwitchClient
        get() {
            return if (ModConfig.username.isBlank() || ModConfig.oauthKey.isBlank()) {
                off()
                println("no credentials")
                TwitchClientBuilder.builder().build()
            } else {
                if (innerClient == null && !enabled) {
                    innerClient = TwitchClientBuilder.builder()
                        .withEnableHelix(true)
                        .withEnablePubSub(true)
                        .withEnableChat(true)
                        .withChatAccount(credentials(ModConfig.oauthKey))
                        .build()
                }

                if (innerClient == null) {
                    off()
                    return TwitchClientBuilder.builder().build()
                }

                innerClient!!
            }
        }


    fun isOn() = enabled
    private fun off() {
        enabled = false
    }

    fun turnOn() {
        client

        if (true) { // Extras
            val resultList = client.helix.getUsers(ModConfig.oauthKey, null, listOf(ModConfig.username))
            val broadcasterId = resultList.execute().users[0].id


            val credential  = credentials(ModConfig.oauthKey)

            client.pubSub.listenForChannelPointsRedemptionEvents(credential, broadcasterId)
            client.eventManager.onEvent(RewardRedeemedEvent::class.java) { channelPointsEvent(it) }

            msgPlayer("extras on")
        }

        enabled = true
        msgPlayer("turend on")
    }

    fun turnOff() {
        if (enabled) {
            off()
            client.pubSub.close()
            client.chat.close()
            client.close()
            innerClient = null
            msgPlayer("turned off")
        } else {
            msgPlayer("already off!!")
        }
    }

    private fun credentials(key: String) =  OAuth2Credential("twitch", key)





    fun msgPlayer(text: String) = println(text)


}