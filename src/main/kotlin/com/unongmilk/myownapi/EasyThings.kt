package com.unongmilk.myownapi

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@Suppress("NAME_SHADOWING", "DEPRECATION")
fun eItemStack(m: Material, name: String, vararg lore: String): ItemStack {
    val i = ItemStack(m)
    val m = i.itemMeta
    m.setDisplayName(name)
    m.lore = lore.asList()
    i.itemMeta = m
    return i
}

fun eItemStackBling(m: Material, name: String, vararg lore: String): ItemStack {
    val i = ItemStack(m)
    val m = i.itemMeta
    m.setDisplayName(name)
    m.lore = lore.asList()
    m.addEnchant(Enchantment.DURABILITY, 1, true)
    m.addItemFlags(ItemFlag.HIDE_ENCHANTS)
    i.itemMeta = m
    return i
}

fun eGui(title: String, line: Int, owner: InventoryHolder? = null): Inventory {
    val inv = Bukkit.createInventory(owner, line * 9, title)
    for (i in 0 until line * 9) {
        inv.setItem(i, eItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, "", ""))
    }
    return inv
}