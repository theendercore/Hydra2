package org.teamvoided.hydra.networking.packages.c2s

import io.netty.buffer.Unpooled
import net.minecraft.network.PacketByteBuf
import org.teamvoided.hydra.networking.packages.HydraPackage

data class JoinServerC2S(val enabled: Boolean, val broadcasterId: String): HydraPackage {
    constructor(buf: PacketByteBuf): this(buf.readBoolean(),buf.readString())
    override fun write(): PacketByteBuf {
        val buf = PacketByteBuf(Unpooled.buffer())
        buf.writeBoolean(enabled)
        buf.writeString(broadcasterId)
        return buf
    }
}