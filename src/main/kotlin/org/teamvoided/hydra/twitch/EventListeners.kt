package org.teamvoided.hydra.twitch

import com.github.twitch4j.pubsub.events.RewardRedeemedEvent
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import org.teamvoided.hydra.networking.NetworkManager
import org.teamvoided.hydra.networking.packages.c2s.ChannelPointsEventC2S


fun channelPointsEvent(event: RewardRedeemedEvent) {
    ClientPlayNetworking.send(NetworkManager.CHANNEL_POINTS_EVENT, ChannelPointsEventC2S("asd").write())
}