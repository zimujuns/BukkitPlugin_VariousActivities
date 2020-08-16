package com.mzimu.variousactivites.decryptkey.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiftPackageList {
    private Map<Integer,ItemStack[]> reward;

    public GiftPackageList(Map<Integer, ItemStack[]> reward) {
        this.reward = reward!=null?reward:new HashMap<>();
    }

    public void setItemStack(int slot,ItemStack[] itemStack){
        reward.put(slot,itemStack);
    }

    public ItemStack[] getItemStack(int slot){
        ItemStack[] itemStack = reward.get(slot);
        return itemStack;
    }

    public Map<Integer, ItemStack[]> getReward() {
        return reward;
    }
}
