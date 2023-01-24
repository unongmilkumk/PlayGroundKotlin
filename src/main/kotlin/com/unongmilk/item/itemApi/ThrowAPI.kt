package com.unongmilk.item.itemApi

import com.unongmilk.Main
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.EulerAngle

class ThrowAPI{
    @Suppress("DEPRECATION")
    fun throwItem(player: Player, toolMaterial: Material, material: Material, cooldown: Int, damage: Double, isTurning: Boolean, main: Main, etc: String ?= null) {
        if (player.getCooldown(toolMaterial) == 0) {
            player.setCooldown(toolMaterial, cooldown)

            val amo = player.world.spawnEntity(player.location.add(0.0, 0.5, 0.0), EntityType.ARMOR_STAND) as ArmorStand

            amo.setArms(true)
            amo.setGravity(false)
            amo.isVisible = false
            amo.isSmall = true
            amo.isMarker = true
            amo.setItemInHand(ItemStack(material))
            amo.rightArmPose = EulerAngle(Math.toRadians(45.0), Math.toRadians(0.0), Math.toRadians(0.0))

            val v = player.location.add(player.location.direction.multiply(10)).subtract(player.location).toVector()

            object : BukkitRunnable() {
                var d = 50
                var i = 0

                override fun run() {
                    if (isTurning) {
                        val rot = amo.rightArmPose.add(7.5, 0.0, 0.0)
                        amo.rightArmPose = rot
                    }

                    amo.teleport(amo.location.add(v.normalize()))
                    if (etc == "dust") {
                        amo.world.spawnParticle(Particle.ASH, amo.location, 1)
                    }

                    if (amo.getTargetBlockExact(1) != null && !amo.getTargetBlockExact(1)!!.isPassable) {
                        if (!amo.isDead) {
                            amo.remove()
                        }
                        cancel()
                    }

                    for (e in amo.location.chunk.entities) {
                        if (!amo.isDead) {
                            if (amo.location.distanceSquared(e.location) <= 1.0) {
                                if (e != player && e != amo) {
                                    if (e is LivingEntity) {
                                        e.damage(damage, player)
                                        if (etc != null) {
                                            if (etc == "anesthesia") {
                                                e.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 60, 10))
                                            }
                                        }
                                        amo.remove()
                                        cancel()
                                    }
                                }
                            }
                        }
                    }

                    if (i > d) {
                        if (!amo.isDead) {
                            amo.remove()
                            cancel()
                        }
                    }

                    i++
                }
            }.runTaskTimer(main, 0L, 1L)
        }
    }
}