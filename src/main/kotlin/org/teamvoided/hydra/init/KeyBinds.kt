package org.teamvoided.hydra.init

import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.option.KeyBind
import org.teamvoided.config.Config
import org.teamvoided.hydra.Hydra.MODID
import org.teamvoided.hydra.networking.NetworkManager.JOIN_SERVER
import org.teamvoided.hydra.networking.packages.c2s.JoinServerC2S
import org.teamvoided.hydra.twitch.TwitchIntegration

object KeyBinds {
    private const val category: String = "key.categories.$MODID"

    val testKey: KeyBind = KeyBindingHelper.registerKeyBinding(KeyBind("testKey", InputUtil.KEY_V_CODE, category))
    val testKey2: KeyBind  = KeyBindingHelper.registerKeyBinding(KeyBind("testKey2", InputUtil.KEY_UP_CODE, category))
    val testKey3: KeyBind  = KeyBindingHelper.registerKeyBinding(KeyBind("testKey3", InputUtil.KEY_DOWN_CODE, category))

    fun init() {
        ClientTickEvents.END_CLIENT_TICK.register {
            if (testKey.wasPressed()) {
                ClientPlayNetworking.send(JOIN_SERVER, JoinServerC2S(true,"asd").write())
                println(Config.prefix)
                println(Config.oauthKey)
            }
            if (testKey2.wasPressed()) TwitchIntegration.turnOn()
            if (testKey3.wasPressed()) TwitchIntegration.turnOff()


        }
    }
}