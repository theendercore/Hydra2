package org.teamvoided.hydra.twitch

import com.github.twitch4j.pubsub.events.FollowingEvent
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import org.teamvoided.hydra.networking.NetworkManager
import org.teamvoided.hydra.networking.packages.c2s.ChannelPointsEventC2S
import org.teamvoided.hydra.networking.packages.c2s.FollowingEventC2S


fun channelPointsEvent(event: RewardRedeemedEvent) {
    val data = event.redemption
    println(data.cursor)
    ClientPlayNetworking.send(
        NetworkManager.CHANNEL_POINTS_EVENT,
        ChannelPointsEventC2S(
            data.reward.id, data.user.displayName, if (data.userInput != null) data.userInput else "", data.status
        ).write()
    )
}

fun followingEvent(event: FollowingEvent) {
    ClientPlayNetworking.send(NetworkManager.FOLLOWING_EVENT, FollowingEventC2S(event.data.displayName).write())
}
