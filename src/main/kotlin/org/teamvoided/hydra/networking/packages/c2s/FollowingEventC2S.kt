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
import org.teamvoided.hydra.networking.NetworkManager.FOLLOWING_EVENT
import org.teamvoided.voidlib.networking.ChainC2SVoidPacket

@Serializable
data class FollowingEventC2S(val displayName: String)

object FollowingEventC2SPacket: ChainC2SVoidPacket<FollowingEventC2S> {
    override val chainId: Identifier = FOLLOWING_EVENT
    @OptIn(ExperimentalSerializationApi::class)
    override val format: BinaryFormat = Cbor { encodeDefaults = true }
    override val serializer: KSerializer<FollowingEventC2S> = FollowingEventC2S.serializer()

    override fun handleC2S(
        server: MinecraftServer,
        player: ServerPlayerEntity,
        handler: ServerPlayNetworkHandler,
        data: FollowingEventC2S,
        responseSender: PacketSender
    ) {
        player.sendMessage(Text.of("${data.displayName} has followed you!"), false)
    }
}
