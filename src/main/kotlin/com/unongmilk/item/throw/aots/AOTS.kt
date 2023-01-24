package com.unongmilk.item.`throw`.aots

import com.unongmilk.myownapi.eItemStack
import com.unongmilk.item.itemApi.ThrowAPI
import com.unongmilk.Main
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class AOTS(main: Main) : Listener {

    var item = eItemStack(Material.DIAMOND_AXE, "${ChatColor.AQUA}Aspect of the Shredded", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    val m = main

    @Suppress("DEPRECATION")
    @EventHandler
    fun aotsInteractEvent(e: PlayerInteractEvent) {
        val p = e.player
        if (p.inventory.itemInMainHand == item) {
            if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
                e.isCancelled = true
                ThrowAPI().throwItem(p, Material.DIAMOND_AXE, Material.DIAMOND_AXE, 40, 4.0, true, main = m)
            }
        }
    }
}