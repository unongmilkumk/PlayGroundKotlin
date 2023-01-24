package com.unongmilk.system.chatting

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent

class NiceChat : Listener {
    @Suppress("DEPRECATION")
    @EventHandler
    fun onChat(e: PlayerChatEvent){
        if(!NoSewing().sewlist.contains(e.message)) {
            e.isCancelled = true
            for (p in Bukkit.getOnlinePlayers()) {
                if (p == e.player) {
                    p.sendMessage("${ChatColor.DARK_GREEN}${p.name}${ChatColor.WHITE} : ${e.message}")
                } else {
                    p.sendMessage("${e.player.name} : ${e.message}")
                }
            }
        }
    }
}