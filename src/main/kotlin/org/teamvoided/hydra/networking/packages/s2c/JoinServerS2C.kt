package org.teamvoided.hydra.networking.packages.s2c

import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.cbor.Cbor
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.util.Identifier
import org.teamvoided.hydra.networking.NetworkManager.JOIN_SERVER
import org.teamvoided.voidlib.networking.ChainS2CVoidPacket

@Serializable
data class JoinServerS2C(val enabled: Boolean)

object JoinServerS2CPacket: ChainS2CVoidPacket<JoinServerS2C> {
    override val chainId: Identifier = JOIN_SERVER
    @OptIn(ExperimentalSerializationApi::class)
    override val format: BinaryFormat = Cbor { encodeDefaults = true }
    override val serializer: KSerializer<JoinServerS2C> = JoinServerS2C.serializer()

    override fun handleS2C(
        client: MinecraftClient,
        handler: ClientPlayNetworkHandler,
        data: JoinServerS2C,
        responseSender: PacketSender
    ) {
        if (data.enabled) {
            //do logic
        }
    }
}
