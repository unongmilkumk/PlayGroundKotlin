package com.unongmilk.item.`throw`.stonaff

import com.unongmilk.Main
import com.unongmilk.myownapi.eItemStack
import com.unongmilk.item.itemApi.ThrowAPI
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class StoneStaff(main: Main): Listener {

    var item = eItemStack(Material.STONE_SHOVEL, "${ChatColor.GRAY}Stone Staff", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    val m = main

    @Suppress("DEPRECATION")
    @EventHandler
    fun stoneStaffInteractEvent(e: PlayerInteractEvent) {
        val p = e.player
        if (p.inventory.itemInMainHand == item) {
            if (e.action == Action.LEFT_CLICK_AIR || e.action == Action.LEFT_CLICK_BLOCK) {
                e.isCancelled = true
                if (p.inventory.itemInOffHand == item) {
                    ThrowAPI().throwItem(p, Material.STONE_SHOVEL, Material.STONE, 2, 4.0, true, main = m)
                } else {
                    ThrowAPI().throwItem(p, Material.STONE_SHOVEL, Material.COBBLESTONE, 2, 2.0, false, main = m)
                }
            }
        }
    }
}