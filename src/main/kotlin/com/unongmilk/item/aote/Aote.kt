package com.unongmilk.item.aote

import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class Aote : Listener {

    var item = eItemStack(Material.DIAMOND_SWORD, "${ChatColor.AQUA}Aspect of the End", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun exampleInteract(e: PlayerInteractEvent) {
        val p = e.player
        val a = e.action
        if (p.inventory.itemInMainHand == item && p.getCooldown(p.inventory.itemInMainHand.type) == 0) {
            if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                var l = p.getTargetBlock(8)?.location
                l?.add(0.0, 1.0, 0.0)
                l?.yaw = p.eyeLocation.yaw
                l?.pitch = p.eyeLocation.pitch
                p.teleport(l!!)
                p.playSound(p.location, Sound.ENTITY_ENDERMAN_TELEPORT, 5.0.toFloat(), 5.0.toFloat())
            }
        }
    }
}