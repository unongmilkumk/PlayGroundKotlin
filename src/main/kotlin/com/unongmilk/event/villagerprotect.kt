package com.unongmilk.event

import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class villagerprotect : Listener{
    @EventHandler
    fun novilatt(e: EntityDamageEvent) {
        if (e.entity is Villager && e.cause != EntityDamageEvent.DamageCause.VOID) {
            e.isCancelled = true
        }
    }
}