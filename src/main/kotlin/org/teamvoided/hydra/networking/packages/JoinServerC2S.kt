package org.teamvoided.hydra.networking.packages

import io.netty.buffer.Unpooled
import net.minecraft.network.PacketByteBuf

class JoinServerC2S(val broadcasterId: String ) {
    constructor(buf: PacketByteBuf): this(buf.readString())
    fun write(): PacketByteBuf {
        val buf = PacketByteBuf(Unpooled.buffer())
        buf.writeString(broadcasterId)
        return buf
    }
}