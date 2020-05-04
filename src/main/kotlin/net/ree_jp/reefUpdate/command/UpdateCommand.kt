package net.ree_jp.reefUpdate.command

import cn.nukkit.Player
import cn.nukkit.Server
import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.command.ConsoleCommandSender
import cn.nukkit.utils.TextFormat
import kotlinx.coroutines.runBlocking
import net.ree_jp.github_artifact_url.ArtifactUrl
import net.ree_jp.reefUpdate.ReefAutoUpdatePlugin
import net.ree_jp.reefUpdate.Update
import net.ree_jp.reefUpdate.event.EventListener


class UpdateCommand(name: String, description: String): Command(name, description) {

    override fun execute(sender: CommandSender?, commandLabel: String?, args: Array<out String>?): Boolean {
        EventListener.isUpdating = true
        if (sender is ConsoleCommandSender) {
            Server.getInstance().broadcastMessage(TextFormat.DARK_GRAY.toString() + ">> " + TextFormat.RESET + "Partially shut down the system and update the plugin from gitlab...")
            for (p in Server.getInstance().onlinePlayers) p.value.kick("Thank you for playing\n\nPreparing to update the server...", false)
            pluginStop()
            Server.getInstance().logger.info(TextFormat.DARK_GRAY.toString() + ">> " + TextFormat.RESET +"processing...")
            Thread.sleep(3000)
            Server.getInstance().logger.info(TextFormat.DARK_GRAY.toString() + ">> " + TextFormat.RESET +"run")
            try {
                run()
                Server.getInstance().logger.info(TextFormat.GREEN.toString() + ">> " + TextFormat.RESET +"complete")
            }catch (ex: Exception) {
                Server.getInstance().logger.info(TextFormat.RED.toString() + ">> " + TextFormat.RESET +ex.message)
            }
        }else if (sender is Player) sender.sendMessage(TextFormat.DARK_GRAY.toString() + ">> " + TextFormat.RESET + "Partially shut down the system and update the plugin from gitlab...")
        return true
    }

    private fun pluginStop() {
        for (plugin in Server.getInstance().pluginManager.plugins)
        {
            if (plugin.value.name != "ReefAutoUpdate") Server.getInstance().pluginManager.disablePlugin(plugin.value)
        }
    }

    private fun run() {
        runBlocking { Update().run(ArtifactUrl("https://api.github.com/repos/ReefNetwork/ReefServer").getUrl("Ree-jp", ReefAutoUpdatePlugin.getInstance().getToken())) }
    }
}