package com.mzimu.variousactivites.decryptkey.event;

import com.mzimu.variousactivites.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditInventory {
    public static final String EDITINVENTORY = "设置界面";
    public static final String TITLE = "设置奖励";
    private Inventory inventoryEdit = Bukkit.createInventory(null,54, EDITINVENTORY);
    private Inventory inventoryGift = Bukkit.createInventory(null,54, TITLE);

    public void edit(Player player,boolean a){
        ItemStack[] itemStacks = Main.confirmUtil.getGui();
        player.closeInventory();
        if(a){
            if(itemStacks!=null){
                inventoryEdit.setContents(itemStacks);
            }
            player.openInventory(inventoryEdit);
        }else{
            if(itemStacks!=null){
                inventoryGift.setContents(itemStacks);
            }
            player.openInventory(inventoryGift);
        }

    }

    public void save(ItemStack[] itemStacks){
        List<ItemStack> list = Arrays.asList(itemStacks);
        if(list.size() != 0){
            Main.confirmUtil.saveGui(list);
        }
    }

}
