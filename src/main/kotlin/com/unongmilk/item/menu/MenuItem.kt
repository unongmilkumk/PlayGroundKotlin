package com.unongmilk.item.menu

import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent

class MenuItem : Listener {

    var item = eItemStack(Material.NETHER_STAR, "${ChatColor.AQUA}메뉴", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun clickInteract(e: PlayerInteractEvent) {
        val p = e.player
        val a = e.action
        if (p.inventory.itemInMainHand == item && p.getCooldown(p.inventory.itemInMainHand.type) == 0) {
            if (a.isRightClick) {
                p.openInventory(Menu.teleportGui())
            }
        }
    }

    @EventHandler
    fun menuClick(e: InventoryClickEvent) {
        if (e.inventory.contents.contentEquals(Menu.teleportGui().contents)) {
            e.isCancelled = true
            when (e.currentItem!!.type) {
                Material.OAK_PLANKS -> {(e.whoClicked as Player).chat("/mv tp @s playground")}
                Material.GRASS_BLOCK -> {(e.whoClicked as Player).chat("/mv tp @s survive")}
                Material.IRON_BLOCK -> {(e.whoClicked as Player).chat("/mv tp @s city")}
                else -> {}
            }
        }
    }
}