package com.unongmilk.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemDamageEvent

class InfDurab : Listener {
    @EventHandler
    fun durab(e: PlayerItemDamageEvent) {
        e.isCancelled = true
    }
}