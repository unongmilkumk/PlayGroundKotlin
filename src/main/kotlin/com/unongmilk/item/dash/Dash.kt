package com.unongmilk.item.dash

import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class Dash : Listener {

    var item = eItemStack(Material.PRISMARINE_SHARD, "${ChatColor.AQUA}Dash", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun exampleInteract(e: PlayerInteractEvent) {
        val p = e.player
        val a = e.action
        if (p.inventory.itemInMainHand == item && p.getCooldown(p.inventory.itemInMainHand.type) == 0) {
            if (e.action == Action.RIGHT_CLICK_BLOCK || e.action == Action.RIGHT_CLICK_AIR) {
                var l = p.getTargetBlock(10)?.location
                l?.add(0.0, 1.0, 0.0)
                l?.yaw = p.eyeLocation.yaw
                l?.pitch = p.eyeLocation.pitch
                p.teleport(l!!)
            }
        }
    }
}