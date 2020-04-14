package net.ree_jp.reefUpdate.event

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerPreLoginEvent

class EventListener : Listener {

    companion object {
        var isUpdating: Boolean = false
    }

    @EventHandler
    fun preLoginForBlockingAccessPlayer(ev: PlayerPreLoginEvent) {
        if (isUpdating) ev.player.kick("ReefAutoUpdate\n\nサーバーの自動更新中です", false)
    }
}