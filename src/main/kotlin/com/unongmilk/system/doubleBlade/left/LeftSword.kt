package com.unongmilk.system.doubleBlade.left

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.ArrayList

val sword = listOf(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD)

@Suppress("DEPRECATION")
fun toLeftSword(itemStack: ItemStack): ItemMeta? {
    val meta = itemStack.itemMeta
    meta.lore = listOf("${ChatColor.BLUE}Left Only : ", "${ChatColor.RED}  Damage Level : +${(1..10).random()}")
    return meta
}