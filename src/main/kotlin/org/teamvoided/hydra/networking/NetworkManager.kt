package org.teamvoided.hydra.networking

import org.teamvoided.template.Template.id

@Suppress("unused")
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

    fun initServer() {

    }

    fun initClient() {

    }

}