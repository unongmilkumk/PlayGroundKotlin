package com.unongmilk.item.gun.machigun

import com.unongmilk.Main
import com.unongmilk.item.itemApi.ThrowAPI
import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class Machigun(private val m: Main) : Listener {

    var item = eItemStack(Material.NETHERITE_HOE, "${ChatColor.AQUA}마취총", "", "${ChatColor.BLUE}PLAYGROUND ITEM")
    var bullet = eItemStack(Material.SLIME_BALL, "${ChatColor.AQUA}마취 총알", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun exampleInteract(e: PlayerInteractEvent) {
        val p = e.player
        val a = e.action
        if (p.inventory.itemInMainHand == item && p.getCooldown(p.inventory.itemInMainHand.type) == 0) {
            if(a.isRightClick) {
                e.isCancelled = true
                ThrowAPI().throwItem(p, item.type, bullet.type,30, 1.0, false, main = m, "anesthesia")
            }
        }
    }
}