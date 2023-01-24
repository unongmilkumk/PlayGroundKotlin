package com.unongmilk.item.menu

import com.unongmilk.myownapi.eGui
import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.Inventory

object Menu {
    fun teleportGui() : Inventory {
        val inv = eGui("${ChatColor.AQUA}Playground Menu", 3)
        inv.setItem(10, eItemStack(Material.OAK_PLANKS, "${ChatColor.YELLOW}놀이터", "${ChatColor.GREEN}놀이터로 이동"))
        inv.setItem(13, eItemStack(Material.GRASS_BLOCK, "${ChatColor.GREEN}야생", "${ChatColor.GOLD}야생으로 이동"))
        inv.setItem(16, eItemStack(Material.IRON_BLOCK, "${ChatColor.WHITE}도시", "${ChatColor.AQUA}도시로 이동"))
        return inv
    }
}