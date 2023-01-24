package com.unongmilk.item.gun.epgun

import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.EnderPearl
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class EnderPearlGun : Listener {

    var item = eItemStack(Material.DIAMOND_HORSE_ARMOR, "${ChatColor.DARK_AQUA}Ender Pearl Gun", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun epgunInteract(e: PlayerInteractEvent) {
        val p = e.player
        val a = e.action
        if (p.inventory.itemInMainHand == item) {
            if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                if (p.getCooldown(p.inventory.itemInMainHand.type) == 0) {
                    p.setCooldown(p.inventory.itemInMainHand.type, 10)
                    p.launchProjectile(EnderPearl::class.java).velocity = p.location.direction
                }
            }
        }
    }
}