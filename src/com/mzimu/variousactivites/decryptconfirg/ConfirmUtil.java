package com.mzimu.variousactivites.decryptconfirg;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.*;

import java.util.*;

public class ConfirmUtil {
    private FileConfiguration fileConfiguration;
    private Plugin plugin;

    public ConfirmUtil(Plugin plugin) {
        this.fileConfiguration = plugin.getConfig();
        this.plugin = plugin;
    }

    public void saveGui(List<ItemStack> list){
        for(int i=0;i<54;i++){
            fileConfiguration.set("Gui."+i,list.get(i));
        }
        plugin.saveConfig();
    }

    public ItemStack[] getGui(){
        ItemStack[] itemStacks = new ItemStack[54];
        for(int i=0;i<54;i++){
            itemStacks[i] = fileConfiguration.getItemStack("Gui."+i);
        }

        return itemStacks;
    }

    @SuppressWarnings("unchecked")
    public boolean[][] getPlayData(String name){
        boolean[][] playMap = fileConfiguration.get("data."+name)!=null ?  utilArrays((List<List<Boolean>>) fileConfiguration.getList("data."+name)) :new boolean[6][6];
        return playMap;
    }

    public void savePlayGui(String name,boolean[][] data){
        fileConfiguration.set("data."+name,data);
        plugin.saveConfig();
    }

    public boolean[][] utilArrays(List<List<Boolean>> list){
        boolean[][] a = new boolean[6][6];
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                a[i][j] = list.get(i).get(j);
            }
        }
        return a;
    }

    public Map<Integer,ItemStack[]> getGiftItemMap(){
        Map<Integer,ItemStack[]> m = new HashMap<>();
        for(int i=0;i<10;i++){
            ItemStack[] itemStacks = new ItemStack[54];
            if(i<5){
                for(int j=0;j<54;j++){
                    itemStacks[i] = fileConfiguration.getItemStack("Gitf." + i*9+5 +"."+j);
                }
                m.put((i*9+5),itemStacks);
            }else{
                for(int j=0;j<54;j++){
                    itemStacks[i] = fileConfiguration.getItemStack("Gitf." + (45+i-5) +"."+j);
                }
                m.put((45+i-5),itemStacks);
            }
        }
        return m;
    }

    public void saveGiftItemMap(Map<Integer,ItemStack[]> m){
        Iterator<Map.Entry<Integer, ItemStack[]>> it = m.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer, ItemStack[]> entry = it.next();
            for(int i=0;i<entry.getValue().length;i++){
                fileConfiguration.set("Gitf." + entry.getKey()+"."+i,entry.getValue()[i]);
            }
        }

        plugin.saveConfig();
    }

}
