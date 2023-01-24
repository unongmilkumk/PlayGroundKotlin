package com.unongmilk.item.randomBox

import com.unongmilk.Main
import com.unongmilk.myownapi.eItemStack
import com.unongmilk.item.`throw`.aots.AOTS
import com.unongmilk.item.`throw`.stonaff.StoneStaff
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class RandomBox(private val m: Main) : Listener {

    var item = eItemStack(Material.HEART_OF_THE_SEA, "${ChatColor.AQUA}뽑기 통", "", "${ChatColor.BLUE}PLAYGROUND ITEM")

    @EventHandler
    fun exampleInteract(e: PlayerInteractEvent) {
        val p = e.player
        val a = e.action
        if (p.inventory.itemInMainHand == item && p.getCooldown(p.inventory.itemInMainHand.type) == 0) {
            if (a.isRightClick) {
                val held = p.inventory.heldItemSlot

                p.inventory.itemInMainHand.amount -= 1

                val random = (0..100).shuffled()[0]
                if ((0..20).contains(random)) {
                    p.inventory.setItem(held, ItemStack(Material.DIAMOND_SWORD))
                    p.sendMessage("${ChatColor.AQUA}통에서 다이아몬드 검이 나왔다")
                }
                if ((21..30).contains(random)) {
                    p.inventory.setItem(held, StoneStaff(main = m).item)
                    p.sendMessage("${ChatColor.YELLOW}${ChatColor.BOLD}통에서 돌 지팡이가 나왔다")
                }
                if ((81..100).contains(random)) {
                    p.freezeTicks = 400
                    p.sendMessage("${ChatColor.WHITE}통에서 찬 냉기가 나왔다")
                }
                if ((41..50).contains(random)) {
                    p.inventory.setItem(held, AOTS(main = m).item)
                    p.sendMessage("${ChatColor.YELLOW}${ChatColor.BOLD}통에서 Axe of the Shredded 가 나왔다")
                }
                if ((51..55).contains(random)) {
                    p.inventory.setItem(held, ItemStack(Material.STICK))
                    //p.sendMessage("${ChatColor.YELLOW}${ChatColor.BOLD}통에서 마법 지팡이가 나왔다!!!")
                    p.sendMessage("${ChatColor.AQUA}통에서 막대기가 나왔다ㅠㅠ")
                }
                if ((56..80).contains(random)) {
                    p.fireTicks = 400
                    p.sendMessage("${ChatColor.RED}통에서 뜨거운 열기가 나왔다")
                }
            }
        }
    }
}