package com.unongmilk.system.region

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.*
import org.bukkit.event.player.PlayerInteractEvent

class RegionTest : Listener {
    private val regionTest = Cuboid(Bukkit.getWorld("playground")!!, -261, 4, 20, -257, 8, 16)

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        if (regionTest.contains(e.block.location)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        if (regionTest.contains(e.block.location)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onFluidMove(e: BlockFromToEvent) {
        if (regionTest.contains(e.block.location)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onInteraction(e: PlayerInteractEvent) {
        if (e.action == Action.RIGHT_CLICK_BLOCK || e.action == Action.LEFT_CLICK_BLOCK) {
            if (regionTest.contains(e.clickedBlock!!.location)) {
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onInteractionBucket(e: PlayerInteractEvent) {
        if (e.action == Action.RIGHT_CLICK_BLOCK) {
            if (regionTest.contains(e.clickedBlock!!.location.add(1.0, 1.0, 1.0))) {
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onBlockPhysical(e: BlockPhysicsEvent) {
        if (regionTest.contains(e.block.location)) {
            e.isCancelled = true
        }
    }
}