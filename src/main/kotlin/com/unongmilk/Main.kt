package com.unongmilk

import com.unongmilk.command.CommandSample
import com.unongmilk.command.HomeSet
import com.unongmilk.command.PlaygroundCommand
import com.unongmilk.event.FarmNoBreak
import com.unongmilk.event.InfDurab
import com.unongmilk.event.villagerprotect
import com.unongmilk.item.`throw`.aots.AOTS
import com.unongmilk.item.`throw`.stonaff.StoneStaff
import com.unongmilk.item.aote.Aote
import com.unongmilk.item.dash.Dash
import com.unongmilk.item.grapplingHook.GrapplingHook
import com.unongmilk.item.gun.epgun.EnderPearlGun
import com.unongmilk.item.gun.machigun.Machigun
import com.unongmilk.item.gun.sniper.Sniper
import com.unongmilk.item.menu.MenuItem
import com.unongmilk.item.randomBox.RandomBox
import com.unongmilk.item.sunDust.SunDust
import com.unongmilk.myownapi.Npc
import com.unongmilk.system.chatting.NiceChat
import com.unongmilk.system.chatting.NoSewing
import com.unongmilk.system.doubleBlade.DoubleBlade
import com.unongmilk.system.doubleBlade.left.LeftCommand
import com.unongmilk.system.privateVault.PrivateVault
import com.unongmilk.system.privateVault.vault
import com.unongmilk.system.region.RegionTest
import com.unongmilk.system.remotePlayer.RemotePlayer
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Main : JavaPlugin() {

    private val m = this@Main

    @Suppress("UNCHECKED_CAST")
    override fun onEnable() {

        saveDefaultConfig()

        if (config.contains("data")) {
            config.getConfigurationSection("data")!!.getKeys(false).forEach { key ->
                val content = (config["data.$key"] as List<ItemStack>).toTypedArray()
                vault[UUID.fromString(key)] = content
            }
        }

        server.getPluginCommand("sample")?.setExecutor(CommandSample())
        server.getPluginCommand("sample")?.tabCompleter = CommandSample()
        server.getPluginCommand("db")?.setExecutor(LeftCommand())
        server.getPluginCommand("pg")?.setExecutor(PlaygroundCommand(m))
        server.getPluginCommand("pg")?.tabCompleter = PlaygroundCommand(m)
        server.getPluginCommand("remote")?.setExecutor(RemotePlayer())
        server.getPluginCommand("remote")?.tabCompleter = RemotePlayer()
        server.getPluginCommand("home")?.setExecutor(HomeSet(m))
        server.getPluginCommand("sethome")?.setExecutor(HomeSet(m))

        server.pluginManager.registerEvents(RandomBox(m), m)
        server.pluginManager.registerEvents(Sniper(m), m)
        server.pluginManager.registerEvents(PrivateVault(), m)
        server.pluginManager.registerEvents(Npc(), m)
        server.pluginManager.registerEvents(RegionTest(), m)
        server.pluginManager.registerEvents(FarmNoBreak(), m)
        server.pluginManager.registerEvents(NoSewing(), m)
        server.pluginManager.registerEvents(NiceChat(), m)
        server.pluginManager.registerEvents(Dash(), m)
        server.pluginManager.registerEvents(MenuItem(), m)
        server.pluginManager.registerEvents(GrapplingHook(), m)
        server.pluginManager.registerEvents(InfDurab(), m)
        server.pluginManager.registerEvents(villagerprotect(), m)
        server.pluginManager.registerEvents(Sniper(m), m)
        server.pluginManager.registerEvents(AOTS(m), m)
        server.pluginManager.registerEvents(StoneStaff(m), m)
        server.pluginManager.registerEvents(DoubleBlade(), m)
        server.pluginManager.registerEvents(Aote(), m)
        server.pluginManager.registerEvents(EnderPearlGun(), m)
        server.pluginManager.registerEvents(Machigun(m), m)
        server.pluginManager.registerEvents(SunDust(m), m)

        logger.info("${ChatColor.GOLD}Playground Plugin Enable")
    }

    override fun onDisable() {
        logger.info("${ChatColor.GOLD}Playground Plugin Disable")

        if (vault.isNotEmpty()) {
            for (entry in vault.entries) config.set("data.${entry.key}", entry.value)
            saveConfig()
        }
    }
}