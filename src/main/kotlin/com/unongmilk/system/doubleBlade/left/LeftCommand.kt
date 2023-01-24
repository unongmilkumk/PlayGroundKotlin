package com.unongmilk.system.doubleBlade.left

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class LeftCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p: Player = sender as Player
        if (label.equals("db", true)) {
            if (sword.contains(p.inventory.itemInMainHand.type)) {
                p.inventory.itemInMainHand.itemMeta = toLeftSword(p.inventory.itemInMainHand)
            }
        }
        return true
    }
}