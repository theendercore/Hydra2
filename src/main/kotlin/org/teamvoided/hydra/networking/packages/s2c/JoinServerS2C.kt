package org.teamvoided.hydra.networking.packages.s2c

import io.netty.buffer.Unpooled
import net.minecraft.network.PacketByteBuf
import org.teamvoided.hydra.networking.packages.HydraPackage

class JoinServerS2C(val enabled: Boolean ) : HydraPackage {
    constructor(buf: PacketByteBuf): this(buf.readBoolean())
    override fun write(): PacketByteBuf {
        val buf = PacketByteBuf(Unpooled.buffer())
        buf.writeBoolean(enabled)
        return buf
    }
}