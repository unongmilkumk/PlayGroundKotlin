package com.unongmilk.myownapi

import com.google.gson.JsonParser
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo
import net.minecraft.server.level.EntityPlayer
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_18_R2.CraftServer
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class Npc: Listener {
    companion object {
        private var npclist = ArrayList<EntityPlayer>()

        @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
        fun createNpc(player: Player, name: String? = null, skin: String? = null) {
            val server = (player.server as CraftServer).server
            val world = (player.world as CraftWorld).handle
            val gameProfile = GameProfile(UUID.randomUUID(), name)
            val npc = EntityPlayer(server, world, gameProfile)

            var skin2 = if (skin != null) { getSkin(player, skin) } else if (name != null) { getSkin(player, name) } else { getSkin(player, player.name) }
            gameProfile.properties.put("textures", Property("textures", skin2[0], skin2[1]))

            npc.a(player.location.x, player.location.y, player.location.z, player.location.yaw, player.location.pitch)

            npclist.add(npc)

            addNPCpacket(npc)
        }

        private fun getSkin(player: Player, name: String): Array<out String> {
            return try {
                val property = JsonParser.parseReader(InputStreamReader(URL("https://sessionserver.mojang.com/sess..." + JsonParser
                    .parseReader(InputStreamReader(URL("https://api.mojang.com/users/profiles...$name")
                        .openStream())).asJsonObject.get("id").asString
                        + "?unsigned=false").openStream())).asJsonObject.get("properties").asJsonArray.get(0).asJsonObject
                arrayOf(property.get("value").asString, property.get("signature").asString)
            } catch (e: Exception) {
                val p = (player as CraftPlayer).handle
                val profile = p.fq()
                val property = profile.properties.get("textures").iterator().next()
                arrayOf(property.value, property.signature)
            }
        }

        private fun addNPCpacket(npc: EntityPlayer) {
            for (p in Bukkit.getOnlinePlayers()) {
                val connection = (p as CraftPlayer).handle.b
                connection.a(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc))
                connection.a(PacketPlayOutNamedEntitySpawn(npc))
                connection.a(PacketPlayOutEntityHeadRotation(npc, (npc.x * 256 / 360).toInt().toByte()))
            }
        }

        private fun Player.addJoinNpcPacket() {
            for (npc in npclist) {
                val connection = (this as CraftPlayer).handle.b
                connection.a(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc))
                connection.a(PacketPlayOutNamedEntitySpawn(npc))
                connection.a(PacketPlayOutEntityHeadRotation(npc, (npc.x * 256 / 360).toInt().toByte()))
            }
        }
        @EventHandler
        fun onJoinNpcPacket(e: PlayerJoinEvent) {
            if (npclist.isNotEmpty()) {
                e.player.addJoinNpcPacket()
                //var reader = NpcRightClickPacketReader()
                //reader.inject(e.player)
            }
        }
        @EventHandler
        fun onQuitNpcPakcet(e: PlayerQuitEvent) {
            //var reader = NpcRightClickPacketReader()
            //reader.uninject(e.player)
        }
    }
}
/*
class NpcRightClickEvent(player: Player, npc: EntityPlayer) : Event(), Cancellable {
    var player: Player
    private var npc: EntityPlayer

    override fun getHandlers(): HandlerList {
        return HandlerList()
    }

    override fun isCancelled(): Boolean {
        return isCancelled
    }

    override fun setCancelled(cancel: Boolean) {
        isCancelled = cancel
    }
}

class NpcRightClickPacketReader() {
    private var channel: Channel? = null
    private var channels: MutableMap<UUID, Channel?> = HashMap()

    fun inject(player: Player) {
        val craftPlayer = player as CraftPlayer
        channel = craftPlayer.handle.b.a.m
        channels[player.getUniqueId()] = channel
        if (channel!!.pipeline()["PacketInjector"] == null) {
            channel!!.pipeline().addAfter("decoder", "PacketInjector", object : MessageToMessageDecoder<Packet<*>>() {
                @Throws(java.lang.Exception::class)
                override fun decode(channel: ChannelHandlerContext, packet: Packet<*>, arg: MutableList<Any>) {
                    arg.add(packet)
                    readPacket(player, packet)
                }
            })
        }
    }

    fun uninject(player: Player) {
        channel = channels[player.uniqueId]
        if (channel!!.pipeline().get("PacketInjector") != null) channel!!.pipeline().remove("PacketInjector")
    }

    fun readPacket(player: Player, packet: Packet<*>?) {

        if (packet!!::class.simpleName!!.equals("PacketPlayInUseEntity", true)) {
            if (getValue(packet, "action").toString().equals("ATTACK", true)) return
            if (getValue(packet, "d").toString().equals("OFF_HAND", true)) return
            if (getValue(packet, "action").toString().equals("INTERACT_AT", true)) return
            if (getValue(packet, "action").toString().equals("INTERACT", true)) return

            var id = getValue(packet, "a").toString().toInt()

            if (getValue(packet, "action").toString().equals("INTERACT", true)) {
                for (npc in Npc.npclist) {
                    if (npc.S == id) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main::class.java), {
                                Bukkit.getPluginManager().callEvent(NpcRightClickEvent(player, npc))
                        }, 0)
                    }
                }
            }
        }
    }

    fun getValue(instance: Any, name: String): Any {
        var result: Any? = null

        try {
            var field = instance::class.java.getDeclaredField(name)
            field.isAccessible = true

            result = field.get(instance)

            field.isAccessible = false
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result ?: 0
    }
}
 */