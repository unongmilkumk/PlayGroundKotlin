package com.unongmilk.command

import com.unongmilk.myownapi.changeSkin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SkinCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p = sender as Player
        if (args.isNotEmpty()) { p.changeSkin(args[0]) }
        return true
    }
}