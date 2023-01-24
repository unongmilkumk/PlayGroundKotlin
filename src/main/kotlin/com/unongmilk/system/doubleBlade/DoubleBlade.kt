package com.unongmilk.system.doubleBlade

import com.unongmilk.system.doubleBlade.left.sword
import org.bukkit.ChatColor
import org.bukkit.Particle
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import kotlin.math.ceil
import kotlin.math.floor

class DoubleBlade : Listener {
    @Suppress("DEPRECATION")
    @EventHandler
    fun doubleBladeEvent(e: EntityDamageByEntityEvent) {
        if (e.damager is Player) {
            //변수 설정
            val p = e.damager as Player
            val l = p.inventory.itemInOffHand

            //오른손에 있는 검이 왼손 검이 아닐시
            if (p.itemInHand.lore() == null || !p.itemInHand.lore!!.contains("${ChatColor.BLUE}Left Only : ")) {
                //왼손 검이 왼손용 검일시
                if (sword.contains(l.type) && l.lore() != null && l.lore!!.contains("${ChatColor.BLUE}Left Only : ")) {
                    //레벨 데미지 설정
                    var ld = l.lore!![l.lore!!.indexOf("${ChatColor.BLUE}Left Only : ") + 1].replace("${ChatColor.RED}  Damage Level : +", "", true).toDouble()
                    //검 데미지 설정
                    for (i in sword.indices) { if (sword[i] == l.type) { ld += i * 0.75 } }
                    //크리티컬 데미지 설정
                    if (e.isCritical) { ld *= 1.3 }
                    //인첸트 데미지 설정
                    if (l.containsEnchantment(Enchantment.DAMAGE_ALL)) {ld += l.getEnchantmentLevel(Enchantment.DAMAGE_ALL) / 3 + 0.5}
                    //최종 데미지
                    e.damage += ceil(ld / 3)
                }
            } else { //왼손 검이 오른손에 있을시
                e.isCancelled = true
                e.entity.world.spawnParticle(Particle.END_ROD, e.entity.location, 30)
            }
        }
    }
}