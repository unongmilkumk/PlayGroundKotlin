package com.unongmilk.myownapi

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo
import org.bukkit.Bukkit

import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer
import org.bukkit.entity.Player
import java.io.InputStreamReader
import java.net.URL


class Skin {
    fun getSkin(name: String): Property? {
        try {
            val url = URL("https://api.mojang.com/users/profiles/minecraft/$name")
            val reader = InputStreamReader(url.openStream())
            val uuid: String = JsonParser.parseReader(reader).asJsonObject.get("id").asString
            val url2 = URL(
                "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid +
                        "?unsigned=false"
            )
            val reader2 = InputStreamReader(url2.openStream())
            val property: JsonObject = JsonParser.parseReader(reader2).asJsonObject.get("properties").asJsonArray.get(0).asJsonObject
            val texture = property["value"].asString
            val signature = property["signature"].asString
            return Property("textures", texture, signature)
        } catch (e: Exception) {
            return null
        }
    }
}

fun Player.changeSkin(name: String) {
    val profile: GameProfile = (this as CraftPlayer).handle.fq()
    for (p in Bukkit.getOnlinePlayers()) {
        val connection = (p as CraftPlayer).handle.b
        connection.a(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, this.handle))
        profile.properties.removeAll("textures")
        profile.properties.put("textures", Skin().getSkin(name))
        connection.a(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, this.handle))
    }
}