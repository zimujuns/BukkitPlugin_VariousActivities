package com.mzimu.variousactivites.decryptkey.trigger;

import com.mzimu.variousactivites.Main;
import com.mzimu.variousactivites.decryptkey.event.EditInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.*;

public class DecryptInventoryClose implements Listener {
    private Plugin plugin;

    public DecryptInventoryClose(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void main(InventoryCloseEvent inventoryCloseEvent){
        Inventory inventory = inventoryCloseEvent.getInventory();
        if(inventory.getTitle().equals(EditInventory.EDITINVENTORY)){
            EditInventory editInventory = new EditInventory();
            editInventory.save(inventory.getContents());
            Main.decryptInventory.setContents(Main.confirmUtil.getGui());
        }else if(inventory.getTitle().equals(EditInventory.TITLE)){
            System.out.println(plugin.getName()+"正在保存奖励箱");
            Main.giftPackageList.setItemStack(Main.rawSlot,inventory.getContents());
            Main.confirmUtil.saveGiftItemMap(Main.giftPackageList.getReward());
            System.out.println(plugin.getName()+"您当前已经保存对奖励箱的操作");
        }
    }
}
