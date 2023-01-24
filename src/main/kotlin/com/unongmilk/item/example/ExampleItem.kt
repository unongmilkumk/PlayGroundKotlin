package com.unongmilk.item.example

import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class ExampleItem : Listener {

    var item = eItemStack(Material.EXPOSED_COPPER, "${ChatColor.AQUA}EXAMPLE", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun exampleInteract(e: PlayerInteractEvent) {
        val p = e.player
        val a = e.action
        if (p.inventory.itemInMainHand == item && p.getCooldown(p.inventory.itemInMainHand.type) == 0) {

        }
    }
}