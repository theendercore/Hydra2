package org.teamvoided.hydra.networking

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.text.Text
import org.teamvoided.hydra.Hydra.id
import org.teamvoided.hydra.networking.packages.c2s.ChannelPointsEventC2S
import org.teamvoided.hydra.networking.packages.c2s.FollowingEventC2S
import org.teamvoided.hydra.networking.packages.c2s.JoinServerC2S
import org.teamvoided.hydra.networking.packages.s2c.JoinServerS2C

//@Suppress("unused")
object NetworkManager {
    //S2C & C2S
    val JOIN_SERVER = id("join_server")

    //C2S
    val CHANNEL_POINTS_EVENT = id("channel_points_event")
    val FOLLOWING_EVENT = id("following_event")
    val HYPE_TRAIN_EVENT = id("hype_train_event")
    val HYPE_TRAIN_REWARD_EVENT = id("hype_train_reward_event")
    val POLL_EVENT = id("poll_event")
    val RAID_EVENT = id("raid_event")
    val SUB_GIFT_EVENT = id("sub_gift_event")
    val SUBSCRIBE_EVENT = id("subscribe_event")
    val COMMUNITY_POINTS_EVENT = id("community_points_event")
    val BITS_EVENT = id("bits_event")

    //S2C
    //add here when needed

    fun initServer() {
        ServerPlayNetworking.registerGlobalReceiver(JOIN_SERVER) { server, player, handler, buf, sender ->
            val pkg = JoinServerC2S(buf)
            if (pkg.enabled) {
                player.sendMessage(Text.of(pkg.broadcasterId), false)


                val pkg2C = JoinServerS2C(true)
                ServerPlayNetworking.send(player, JOIN_SERVER, pkg2C.write())
            }
        }

        ServerPlayNetworking.registerGlobalReceiver(CHANNEL_POINTS_EVENT) { server, player, handler, buf, sender ->
            val pkg = ChannelPointsEventC2S(buf)
            player.sendMessage(Text.of("${pkg.id} ${pkg.displayName} ${pkg.userInput} ${pkg.status}"), false)
        }
        ServerPlayNetworking.registerGlobalReceiver(FOLLOWING_EVENT) { server, player, handler, buf, sender ->
            val name = FollowingEventC2S(buf).displayName
            player.sendMessage(Text.of("$name has followed you!"), false)
        }

    }

    fun initClient() {
        ClientPlayNetworking.registerGlobalReceiver(JOIN_SERVER) { client, handler, buf, sender ->
            val pkg = JoinServerS2C(buf)
            if (pkg.enabled) {
                //do logic
            }
        }

    }

}