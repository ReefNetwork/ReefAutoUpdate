package net.ree_jp.reefUpdate

import cn.nukkit.plugin.PluginBase
import net.ree_jp.reefUpdate.command.UpdateCommand

class ReefAutoUpdatePlugin: PluginBase() {

    override fun onEnable() {
        server.commandMap.register("reefUpdate", UpdateCommand("reefupdate", "Stop server processing and update plugins"))
        super.onEnable()
    }
}