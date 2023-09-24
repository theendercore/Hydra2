package org.teamvoided.hydra.networking.packages.c2s

import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.cbor.Cbor
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.teamvoided.hydra.networking.NetworkManager
import org.teamvoided.hydra.networking.packages.s2c.JoinServerS2C
import org.teamvoided.hydra.networking.packages.s2c.JoinServerS2CPacket
import org.teamvoided.voidlib.networking.ChainC2SVoidPacket
import org.teamvoided.voidlib.networking.Packets.sendToClient

@Serializable
data class JoinServerC2S(val enabled: Boolean, val broadcasterId: String)

object JoinServerC2SPacket: ChainC2SVoidPacket<JoinServerC2S> {
    override val chainId: Identifier = NetworkManager.JOIN_SERVER
    @OptIn(ExperimentalSerializationApi::class)
    override val format: BinaryFormat = Cbor { encodeDefaults = true }
    override val serializer: KSerializer<JoinServerC2S> = JoinServerC2S.serializer()

    override fun handleC2S(
        server: MinecraftServer,
        player: ServerPlayerEntity,
        handler: ServerPlayNetworkHandler,
        data: JoinServerC2S,
        responseSender: PacketSender
    ) {
        if (data.enabled) {
            player.sendMessage(Text.of(data.broadcasterId), false)

            val pkg2C = JoinServerS2C(true)
            JoinServerS2CPacket.sendToClient(player, pkg2C)
        }
    }
}