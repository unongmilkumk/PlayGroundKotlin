package com.unongmilk.command

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.*


class CommandSample : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p: Player = sender as Player
        if (label.equals("sample", true)) {
            p.sendMessage("${ChatColor.GOLD}this is sample for developer")
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        return if (alias.equals("sample", true)) {
            if (args.size === 1) {
                val returns1: MutableList<String> = ArrayList()
                returns1.add("sampleTab")
                val returns2: MutableList<String> = ArrayList()
                for (returns in returns1) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[0].lowercase(Locale.getDefault()))) {
                        returns2.add(returns)
                    }
                }
                returns2
            } else if (args.size === 2) {
                val returns1: MutableList<String> = ArrayList()
                if (args[0].equals("set", true)) {
                    returns1.add("sampleTab2")
                } else {
                    return mutableListOf("")
                }
                val returns2: MutableList<String> = ArrayList()
                for (returns in returns1) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[1].lowercase(Locale.getDefault()))) {
                        returns2.add(returns)
                    }
                }
                return returns2
            } else {
                return mutableListOf("")
            }
        } else return null
    }

}