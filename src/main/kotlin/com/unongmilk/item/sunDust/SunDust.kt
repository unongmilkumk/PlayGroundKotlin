package com.unongmilk.item.sunDust

import com.unongmilk.Main
import com.unongmilk.item.itemApi.ThrowAPI
import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent


class SunDust(main: Main): Listener {

    private val m = main

    var item = eItemStack(Material.GLOWSTONE_DUST, "${ChatColor.GOLD}Sun Dust", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun sunDustInteractEvent(e: PlayerInteractEvent) {
        val p = e.player
        if (p.inventory.itemInMainHand == item && p.getCooldown(item.type) == 0) {
            if (e.action.isRightClick) {
                e.isCancelled = true
                ThrowAPI().throwItem(p, item.type, item.type,50, 20.0, false, main = m)
            }
        }
    }
}