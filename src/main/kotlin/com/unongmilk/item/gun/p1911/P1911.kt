package com.unongmilk.item.gun.p1911

import com.unongmilk.Main
import com.unongmilk.item.itemApi.ThrowAPI
import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent


class P1911(main: Main): Listener {

    private val m = main

    var item = eItemStack(Material.IRON_HORSE_ARMOR, "${ChatColor.AQUA}P1911", "", "${ChatColor.BLUE}PLAYGROUND ITEM")
    var bullet = eItemStack(Material.IRON_NUGGET, "${ChatColor.AQUA}.45", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun p1911InteractEvent(e: PlayerInteractEvent) {
        val p = e.player
        if (p.inventory.itemInMainHand == item && p.getCooldown(item.type) == 0) {
            if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
                e.isCancelled = true
                ThrowAPI().throwItem(p, item.type, bullet.type,20, 4.0, false, main = m)
            }
        }
    }
}