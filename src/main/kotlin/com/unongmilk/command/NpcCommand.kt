package com.unongmilk.command

import com.unongmilk.myownapi.Npc
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class NpcCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        when (args.size) {
            0 -> Npc.createNpc(sender as Player)
            1 -> Npc.createNpc(sender as Player, args[0])
            2 -> Npc.createNpc(sender as Player, args[0], args[1])
        }
        return true
    }
}