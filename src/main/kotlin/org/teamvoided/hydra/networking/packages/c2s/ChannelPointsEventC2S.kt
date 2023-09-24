package org.teamvoided.hydra.networking.packages.c2s

import io.netty.buffer.Unpooled
import net.minecraft.network.PacketByteBuf
import org.teamvoided.hydra.networking.packages.HydraPackage

data class ChannelPointsEventC2S(
    val id: String, val displayName: String, val userInput: String, val status: String,
) : HydraPackage {
    constructor(buf: PacketByteBuf) : this(buf.readString(), buf.readString(), buf.readString(), buf.readString())

    override fun write(): PacketByteBuf {
        val buf = PacketByteBuf(Unpooled.buffer())
        buf.writeString(id)
        buf.writeString(displayName)
        buf.writeString(userInput)
        buf.writeString(status)
        return buf
    }
}