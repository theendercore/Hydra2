package org.teamvoided.hydra

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import net.minecraft.text.Text
import org.teamvoided.hydra.Hydra.MODID

object TwitchIntegration {
    private var innerClient: TwitchClient? = null

    val client: TwitchClient
        get() {
            return if (ModConfig.username.isBlank() || ModConfig.oauthKey.isBlank()) {
                mcChatMethod(Text.translatable("$MODID.twitch.oauth.no_credentials"))
                TwitchClientBuilder.builder().build()
            } else {
                if (innerClient == null) innerClient = TwitchClientBuilder.builder()
                    .withEnableHelix(true)
                    .withEnablePubSub(true)
                    .withEnableChat(true)
                    .withChatAccount(OAuth2Credential("twitch", ModConfig.oauthKey))
                    .build()

                if (innerClient == null) {
                    mcChatMethod(Text.translatable("$MODID.twitch.oauth.failed"))
                    return TwitchClientBuilder.builder().build()
                }

                innerClient!!
            }
        }

    fun mcChatMethod(text: Text) = println(text.toString())
//        MinecraftClient.getInstance().inGameHud.chatHud.addMessage(text)
}