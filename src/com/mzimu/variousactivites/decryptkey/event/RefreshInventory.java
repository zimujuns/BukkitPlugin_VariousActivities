package com.mzimu.variousactivites.decryptkey.event;

import com.mzimu.variousactivites.Main;
import com.mzimu.variousactivites.decryptkey.data.PlayData;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RefreshInventory {
    private Inventory i;

    public RefreshInventory(Inventory i) {
        this.i=i;
    }

    public ItemStack[] refresh(PlayData playData){
        ItemStack itemStack = new ItemStack(Material.PURPUR_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = new ArrayList<>();
        list.add("您已经获取过这个奖励了");
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        for(int i=0;i<5;i++){
            if(playData.getGuiList(i,5)){
                this.i.setItem(i*9+5,itemStack);
            }
        }
        for(int i=0;i<5;i++){
            if(playData.getGuiList(5,i)){
                this.i.setItem(45+i,itemStack);
            }
        }
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(playData.getGuiList(i,j)){
                    this.i.setItem(i*9+j,new ItemStack(Material.EGG));
                }
            }
        }
        list.clear();
        list.add("");
        list.add("您当前积分剩余");
        list.add(playData.getNumber()+"");
        list.add("当积分满100时即可进行");
        list.add("指向解密。");
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        itemStack.setType(Main.confirmUtil.getGui()[53].getType());
        this.i.setItem(53,itemStack);
        return this.i.getContents();
    }

}
