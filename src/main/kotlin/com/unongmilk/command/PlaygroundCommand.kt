package com.unongmilk.command

import com.unongmilk.Main
import com.unongmilk.item.`throw`.aots.AOTS
import com.unongmilk.item.`throw`.stonaff.StoneStaff
import com.unongmilk.item.aote.Aote
import com.unongmilk.item.dash.Dash
import com.unongmilk.item.grapplingHook.GrapplingHook
import com.unongmilk.item.gun.epgun.EnderPearlGun
import com.unongmilk.item.gun.machigun.Machigun
import com.unongmilk.item.gun.p1911.P1911
import com.unongmilk.item.gun.sniper.Sniper
import com.unongmilk.item.menu.MenuItem
import com.unongmilk.item.randomBox.RandomBox
import com.unongmilk.item.sunDust.SunDust
import com.unongmilk.system.privateVault.vault
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.*

val cy = ChatColor.YELLOW
val ca = ChatColor.AQUA
val cw = ChatColor.WHITE

class PlaygroundCommand(main: Main) : CommandExecutor, TabCompleter {
    private val m = main
    @Suppress("DEPRECATION")
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p = sender as Player
        if (label.equals("pg", true)) {
            if (args.isNotEmpty()) {
                when (args[0]) {
                    "item" -> {
                        when (args[1]) {
                            "grappling_hook" -> {
                                p.inventory.addItem(GrapplingHook().item)
                            }
                            "p1911" -> {
                                p.inventory.addItem(P1911(m).item)
                            }
                            "bullet_45" -> {
                                p.inventory.addItem(P1911(m).bullet)
                            }
                            "ender_pearl_gun" -> {
                                p.inventory.addItem(EnderPearlGun().item)
                            }
                            "aote" -> {
                                p.inventory.addItem(Aote().item)
                            }
                            "aots" -> {
                                p.inventory.addItem(AOTS(main = m).item)
                            }
                            "random_box" -> {
                                p.inventory.addItem(RandomBox(m).item)
                            }
                            "dash" -> {
                                p.inventory.addItem(Dash().item)
                            }
                            "stone_staff" -> {
                                p.inventory.addItem(StoneStaff(main = m).item)
                            }
                            "menu_item" -> {
                                p.inventory.addItem(MenuItem().item)
                            }
                            "anesthesia_gun" -> {
                                p.inventory.addItem(Machigun(m = m).item)
                            }
                            "anesthesia_bullet" -> {
                                p.inventory.addItem(Machigun(m = m).bullet)
                            }
                            "sniper" -> {
                                p.inventory.addItem(Sniper(main = m).item)
                            }
                            "sun_dust" -> {
                                p.inventory.addItem(SunDust(main = m).item)
                            }
                        }
                    }
                    "info" -> {
                        p.sendMessage("$cy 서버 정보 \n$ca 주소 $cw: $cy( 현재 하마치 서버 ) \n$ca 공식 개설일 $cw:$cy 2021년 8월 25일 ")
                    }
                    "vault" -> {
                        val inv = Bukkit.createInventory(p, 54, "${ChatColor.GREEN}${p.name}님의 개인 창고")
                        if (vault.containsKey(p.uniqueId)) inv.contents = vault[p.uniqueId]
                        p.openInventory(inv)
                    }
                    "glowing" -> {
                        p.isGlowing = false
                    }
                }
            }
        }
        return true
    }
    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        if (alias.equals("pg", true)) {
            if (args.size === 1) {
                val returns1: MutableList<String> = arrayListOf("item", "info", "vault")
                val returns2: MutableList<String> = ArrayList()
                for (returns in returns1) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[0].lowercase(Locale.getDefault()))) {
                        returns2.add(returns)
                    }
                }
                return returns2
            } else if (args.size === 2 && args[0] == "item") {
                val returns3: MutableList<String> = arrayListOf("grappling_hook", "p1911", "bullet_45", "ender_pearl_gun", "aote",
                    "aots", "stone_staff", "dash", "menu_item", "random_box", "anesthesia_gun", "anesthesia_bullet", "sniper", "sun_dust")
                val returns4: MutableList<String> = ArrayList()
                for (returns in returns3) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[1].lowercase(Locale.getDefault()))) {
                        returns4.add(returns)
                    }
                }
                return returns4
            } else {
                return mutableListOf("")
            }
        } else return null
    }
}