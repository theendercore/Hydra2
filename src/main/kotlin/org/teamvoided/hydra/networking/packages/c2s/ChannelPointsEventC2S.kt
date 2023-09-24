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
import org.teamvoided.hydra.networking.NetworkManager.CHANNEL_POINTS_EVENT
import org.teamvoided.voidlib.networking.ChainC2SVoidPacket

@Serializable
data class ChannelPointsEventC2S(
    val id: String, val displayName: String, val userInput: String, val status: String,
)

object ChannelPointsEventC2SPacket: ChainC2SVoidPacket<ChannelPointsEventC2S> {
    override val chainId: Identifier = CHANNEL_POINTS_EVENT
    @OptIn(ExperimentalSerializationApi::class)
    override val format: BinaryFormat = Cbor { encodeDefaults = true }
    override val serializer: KSerializer<ChannelPointsEventC2S> = ChannelPointsEventC2S.serializer()

    override fun handleC2S(
        server: MinecraftServer,
        player: ServerPlayerEntity,
        handler: ServerPlayNetworkHandler,
        data: ChannelPointsEventC2S,
        responseSender: PacketSender
    ) {
        player.sendMessage(Text.of("${data.id} ${data.displayName} ${data.userInput} ${data.status}"), false)
    }
}
