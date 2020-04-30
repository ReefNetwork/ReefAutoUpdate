package net.ree_jp.reefUpdate

import cn.nukkit.plugin.PluginBase
import net.ree_jp.reefUpdate.command.UpdateCommand

class ReefAutoUpdatePlugin: PluginBase() {

    companion object{

        private lateinit var instance: ReefAutoUpdatePlugin

        fun getInstance(): ReefAutoUpdatePlugin {
            return instance
        }
    }

    override fun onEnable() {
        instance = this
        dataFolder.mkdir()
        saveDefaultConfig()
        server.commandMap.register("reefUpdate", UpdateCommand("reefupdate", "Stop server processing and update plugins"))
        super.onEnable()
    }

    fun getToken(): String {
        return config.get("token", String())
    }
}