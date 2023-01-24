package com.unongmilk.item.grapplingHook

import com.unongmilk.myownapi.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.util.Vector

@Suppress("DEPRECATION")
class GrapplingHook: Listener {
    var item = eItemStack(Material.FISHING_ROD, "${ChatColor.GREEN}GRAPPLING HOOK", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun grapplingEvent(e: PlayerFishEvent) {
        if (e.player.inventory.itemInMainHand == item) {
            if (e.state == PlayerFishEvent.State.REEL_IN || e.state == PlayerFishEvent.State.IN_GROUND) {
                val p = e.player
                val loc1: Location = p.location
                val loc2: Location = e.hook.location

                val v = Vector(loc2.x - loc1.x, 1.0, loc2.z - loc1.z)
                p.velocity = v
            }
        }
    }
}