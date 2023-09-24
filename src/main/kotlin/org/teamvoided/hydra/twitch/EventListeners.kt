package org.teamvoided.hydra.twitch

import com.github.twitch4j.pubsub.events.FollowingEvent
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent
import org.teamvoided.hydra.networking.packages.c2s.ChannelPointsEventC2S
import org.teamvoided.hydra.networking.packages.c2s.ChannelPointsEventC2SPacket
import org.teamvoided.hydra.networking.packages.c2s.FollowingEventC2S
import org.teamvoided.hydra.networking.packages.c2s.FollowingEventC2SPacket
import org.teamvoided.voidlib.networking.Packets.sendToServer


fun channelPointsEvent(event: RewardRedeemedEvent) {
    val data = event.redemption
    println(data.cursor)
    ChannelPointsEventC2SPacket.sendToServer(
        ChannelPointsEventC2S(
            data.reward.id, data.user.displayName, if (data.userInput != null) data.userInput else "", data.status
        )
    )
}

fun followingEvent(event: FollowingEvent) {
    FollowingEventC2SPacket.sendToServer(FollowingEventC2S(event.data.displayName))
}
