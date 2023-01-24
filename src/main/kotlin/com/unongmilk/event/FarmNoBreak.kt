package com.unongmilk.event

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockDropItemEvent
import org.bukkit.event.entity.EntityInteractEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class FarmNoBreak : Listener {
    @EventHandler
    fun farmnobreak(e: PlayerInteractEvent) {
        if (e.action == Action.PHYSICAL) {
            if (e.clickedBlock!!.type == Material.FARMLAND) {
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    fun farmnobreak2(e: EntityInteractEvent) {
        if (e.block.type == Material.FARMLAND) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun farmbreakstay(e: BlockDropItemEvent) {
        if (e.block.type == Material.FARMLAND) {
            for (i in 0 until e.items.size) {
                if (e.items[i].itemStack.type == Material.DIRT) {
                    e.items.removeAt(i)
                    e.block.world.dropItemNaturally(e.block.location.add(0.5, 0.5, 0.5), ItemStack(Material.FARMLAND))
                }
            }
        }
    }
}