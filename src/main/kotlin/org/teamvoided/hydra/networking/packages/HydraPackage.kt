package org.teamvoided.hydra.networking.packages

import net.minecraft.network.PacketByteBuf

interface HydraPackage {
    fun write(): PacketByteBuf
}