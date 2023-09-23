package org.teamvoided.hydra

import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.hydra.init.CommandRegistry
import org.teamvoided.hydra.init.KeyBinds
import org.teamvoided.hydra.init.StreamActionRegistry
import org.teamvoided.hydra.networking.NetworkManager


@Suppress("unused")
object Hydra {
    const val MODID = "hydra"

    @JvmField
    val LOGGER = LoggerFactory.getLogger(Hydra::class.java)

    fun commonInit() {
        LOGGER.info("Hello from Common")
        StreamActionRegistry.init()
        CommandRegistry.init()
        KeyBinds.init()
        NetworkManager.initServer()

//        val resultList = TwitchIntegration.client.helix.getUsers(ModConfig.oauthKey, null, listOf(ModConfig.username)).execute()
//        val bd = resultList.users[0].id
//        println(bd)
//        val helpA = TwitchIntegration.client.helix.getCustomRewards(ModConfig.oauthKey, bd, null, null).execute()
//        for (z in helpA.rewards) {
//            println(z.title)
//        }

    }

    fun clientInit() {
        LOGGER.info("Hello from Client")
        NetworkManager.initClient()
    }

    fun id(path: String) = Identifier(MODID, path)
    fun mc(path: String) = Identifier(path)
}