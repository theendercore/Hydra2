package org.teamvoided.hydra.networking.packages.c2s

import io.netty.buffer.Unpooled
import net.minecraft.network.PacketByteBuf
import org.teamvoided.hydra.networking.packages.HydraPackage

class ChannelPointsEventC2S(val stuff: String) : HydraPackage {
    constructor(buf: PacketByteBuf) : this(buf.readString())

    override fun write(): PacketByteBuf {
        val buf = PacketByteBuf(Unpooled.buffer())
        buf.writeString(stuff)
        return buf
    }
}