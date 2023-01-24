package com.unongmilk.system.remotePlayer

import com.unongmilk.myownapi.changeSkin
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.*

class RemotePlayer: CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (label.equals("remote", true)) {
            if (args.isNotEmpty()) {
                for (i in Bukkit.getOnlinePlayers()) {
                    if (args[0] == i.name && i != sender as Player) {
                        this.remote(sender, i)
                    }
                }
            } else {
                unremote(sender as Player)
            }
        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        return if (alias.equals("remote", true)) {
            if (args.size == 1) {
                val returns1: MutableList<String> = ArrayList()
                for (i in Bukkit.getOnlinePlayers()) {
                    returns1.add(i.name)
                }
                val returns2: MutableList<String> = ArrayList()
                for (returns in returns1) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[0].lowercase(Locale.getDefault()))) {
                        returns2.add(returns)
                    }
                }
                returns2
            } else {
                return mutableListOf("")
            }
        } else return null
    }

    fun remote(player: Player, targetPlayer: Player) {
        targetPlayer.gameMode = GameMode.SPECTATOR
        targetPlayer.spectatorTarget = player
        player.changeSkin(targetPlayer.name)
        player.teleport(targetPlayer)
    }

    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    fun unremote(player: Player) {
        var d = Double.MAX_VALUE
        var lp = Bukkit.getOnlinePlayers().shuffled()[0]
        for (i in Bukkit.getOnlinePlayers()) {
            if (i != player) {
                if (d < i.location.distance(player.location)) { d = i.location.distance(player.location); lp = i }
            }
        }
        lp.gameMode = GameMode.SURVIVAL
        player.changeSkin(player.name)
    }
}