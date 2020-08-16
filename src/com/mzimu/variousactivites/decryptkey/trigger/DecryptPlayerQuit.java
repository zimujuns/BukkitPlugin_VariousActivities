package com.mzimu.variousactivites.decryptkey.trigger;

import com.mzimu.variousactivites.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.*;

public class DecryptPlayerQuit implements Listener {
    private Plugin plugin;

    public DecryptPlayerQuit(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void main(PlayerQuitEvent playerQuitEvent){
        Player player = playerQuitEvent.getPlayer();
        Main.confirmUtil.savePlayGui(player.getName(),Main.playDataMap.get(player.getName()).getGuiList());
        Main.playDataMap.remove(player.getName());
    }
}
