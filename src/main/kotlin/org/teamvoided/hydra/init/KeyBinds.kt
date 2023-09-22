package org.teamvoided.hydra.init

import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.option.KeyBind
import org.teamvoided.hydra.networking.NetworkManager.JOIN_SERVER
import org.teamvoided.hydra.networking.packages.JoinServerC2S

object KeyBinds {
    val testKey = KeyBind("yes", InputUtil.KEY_V_CODE, "yes")

    fun init() {
        ClientTickEvents.END_CLIENT_TICK.register {
            if (testKey.wasPressed()) {
                ClientPlayNetworking.send(JOIN_SERVER, JoinServerC2S("asd").write())
            }

        }
    }
}