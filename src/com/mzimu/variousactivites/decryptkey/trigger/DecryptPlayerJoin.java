package com.mzimu.variousactivites.decryptkey.trigger;

import com.mzimu.variousactivites.Main;
import com.mzimu.variousactivites.decryptkey.data.PlayData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.*;

public class DecryptPlayerJoin implements Listener {
    private Plugin plugin;

    public DecryptPlayerJoin(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void main(PlayerJoinEvent playerJoinEvent){
        Player player = playerJoinEvent.getPlayer();
        PlayData playData = new PlayData(player);
        playData.setGuiList(Main.confirmUtil.getPlayData(player.getName()));
        Main.playDataMap.put(player.getName(),playData);

    }
}
