package com.unongmilk.system.privateVault

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack
import java.util.*

var vault = HashMap<UUID, Array<out ItemStack>>()

class PrivateVault : Listener {
    @EventHandler
    fun onGuiClose(e: InventoryCloseEvent) {
        if (e.view.title == "${ChatColor.GREEN}${e.player.name}님의 개인 창고") if (!e.inventory.contents.isNullOrEmpty()) {
            val contentslistarray = ArrayList<ItemStack>()
            for (i in e.inventory.contents!!) {
                if (i != null) {
                    contentslistarray.add(i)
                } else {
                    contentslistarray.add(ItemStack(Material.AIR))
                }
            }
            vault[e.player.uniqueId] = contentslistarray.toTypedArray()
        }
    }
}