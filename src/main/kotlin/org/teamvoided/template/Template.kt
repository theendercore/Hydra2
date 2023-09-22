package org.teamvoided.template

import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import org.teamvoided.hydra.ModConfig
import org.teamvoided.hydra.TwitchIntegration
import org.teamvoided.template.init.CommandRegistry
import org.teamvoided.template.init.StreamActionRegistry


@Suppress("unused")
object Template {
    const val MODID = "template"

    @JvmField
    val LOGGER = LoggerFactory.getLogger(Template::class.java)

    fun commonInit() {
        LOGGER.info("Hello from Common")
        StreamActionRegistry.init()
        CommandRegistry.init()

        val resultList = TwitchIntegration.client.helix.getUsers(ModConfig.oauthKey, null, listOf(ModConfig.username)).execute()
        val bd = resultList.users[0].id
        println(bd)
        val helpA = TwitchIntegration.client.helix.getCustomRewards(ModConfig.oauthKey, bd, null, null).execute()
        for (z in helpA.rewards) {
            println(z.title)
        }

    }

    fun clientInit() {
        LOGGER.info("Hello from Client")
    }

    fun id(path: String) = Identifier(MODID, path)
    fun mc(path: String) = Identifier(path)
}