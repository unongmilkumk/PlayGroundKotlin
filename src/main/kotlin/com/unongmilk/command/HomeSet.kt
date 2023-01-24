package com.unongmilk.command

import com.unongmilk.myownapi.Config
import com.unongmilk.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class HomeSet(main: Main) : CommandExecutor {
    private val main = main
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p = sender as Player
        val config = Config(main).loadConfig(p)
        if (label.equals("sethome", true)) {
            if (config.getconfig()!!.getLocation("player.${p.uniqueId}.location") != null) {
                p.teleport(config.getconfig()!!.getLocation("player.${p.uniqueId}.location")!!)
            }
        } else if (label.equals("home", true)) {
            config.getconfig()!!.set("player.${p.uniqueId}.location", p.location)
        }
        return true
    }
}