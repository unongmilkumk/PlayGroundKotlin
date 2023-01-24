package com.unongmilk.item.gun.sniper

import com.unongmilk.Main
import com.unongmilk.item.itemApi.ThrowAPI
import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent


class Sniper(main: Main): Listener {

    private val m = main

    var item = eItemStack(Material.SPYGLASS, "${ChatColor.AQUA}M24", "", "${ChatColor.BLUE}PLAYGROUND ITEM")
    private var bullet = eItemStack(Material.GOLD_NUGGET, "${ChatColor.AQUA}7.62mm", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun sniperInteractEvent(e: PlayerInteractEvent) {
        val p = e.player
        if (p.inventory.itemInMainHand == item && p.getCooldown(Material.SPYGLASS) == 0) {
            if (e.action.isLeftClick) {
                e.isCancelled = true
                ThrowAPI().throwItem(p, item.type, bullet.type,60, 15.0, false, main = m, "dust")
                p.playSound(p.location, Sound.BLOCK_WOODEN_DOOR_CLOSE, 10f, 2f)
                p.teleport(Location(p.world, p.location.x, p.location.y, p.location.z, p.location.yaw, p.location.pitch - 10))
            }
        }
    }
}