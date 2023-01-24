package com.unongmilk.system.chatting

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent

class NoSewing : Listener {
    var sewlist = arrayOf("fuck", "shit", "씨발", "시발", "좆", "새끼", "지랄", "병신", "빡대가리")
    @Suppress("DEPRECATION")
    @EventHandler
    fun onChat(e: PlayerChatEvent){
        for (l in sewlist) {
            if (e.message.contains(l)) {
                e.isCancelled = true
                for (i in Bukkit.getOnlinePlayers()) {
                    i.sendMessage("${ChatColor.RED}- ${e.player.name}님의 메시지에 욕이 들어가있습니다 -")
                }
            }
        }
    }
}