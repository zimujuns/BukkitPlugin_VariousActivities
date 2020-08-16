package com.mzimu.variousactivites.decryptkey.trigger;

import com.mzimu.variousactivites.Main;
import com.mzimu.variousactivites.decryptkey.DecryptMain;
import com.mzimu.variousactivites.decryptkey.data.PlayData;
import com.mzimu.variousactivites.decryptkey.event.EditInventory;
import com.mzimu.variousactivites.decryptkey.event.IsSoltLink;
import com.mzimu.variousactivites.decryptkey.event.RandomSlot;
import com.mzimu.variousactivites.decryptkey.event.RefreshInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.*;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class DecryptInventoryClick implements Listener {
    private Plugin plugin;

    public DecryptInventoryClick(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }


    @EventHandler
    public void main(InventoryClickEvent inventoryClickEvent){
        Inventory inventory = inventoryClickEvent.getInventory();
        if(inventory==null || inventoryClickEvent.getRawSlot()<0){
            return;
        }

        int RawSlot = inventoryClickEvent.getRawSlot();
        Player player = (Player) inventoryClickEvent.getWhoClicked();
        if(inventory.getTitle().equals(DecryptMain.InventoryTitle)){
            inventoryClickEvent.setCancelled(true);
            switch (RawSlot) {
                case 52:
                    PlayData playData = Main.playDataMap.get(player.getName());
                    if(playData.getEditGui()){
                        player.sendMessage("您不可以在指定解密模式下，进行刷新!");
                        playData.setEditGui(false);
                        player.closeInventory();
                        player.openInventory(inventory);
                        return;
                    }
                    RandomSlot randomSlot = new RandomSlot();
                    int i = randomSlot.getRandomInt();
                    int j = randomSlot.getRandomInt();

                    if (!playData.getGuiList(i, j)) {
                        //判断为空 为没有被赋值的份
                        playData.setGuiList(i, j, true);
                        refreshKit(playData,player,i,j,RawSlot);
                        refresh(player,inventory,playData);
                    }else{
                        playData.addNumber(10);
                        player.sendMessage("可惜是个重复的位置,积分+10");
                        /**
                         * 翻卡翻到重复的卡时候
                         * 对玩家的积分进行上涨
                         * 并且对其增加消息提醒
                         */
                        inventory.setItem(53,refreshLore(playData.getNumber()));
                    }
                    break;
                case 53:
                    int number = Main.playDataMap.get(player.getName()).getNumber();
                    if(number>=100){
                        Main.playDataMap.get(player.getName()).setEditGui(true);
                    }else{
                        player.sendMessage("您现在的积分不足!当前积分" + number);
                    }
                    break;
                case 25:
                    player.closeInventory();
                    break;
                default:
                    PlayData pd = Main.playDataMap.get(player.getName());
                    int y = RawSlot/9,x=RawSlot%9;
                    if(pd.getEditGui()){
                        if(!pd.getGuiList(y,x)){
                            pd.setGuiList(y,x,true);
                            pd.delNumber(100);
                            pd.setEditGui(false);
                            refresh(player,inventory,pd);
                            refreshKit(pd,player,y,x,RawSlot);
                            player.sendMessage("您已经激活了第" + x+"行,第" + y + "列");
                        }else{
                            player.sendMessage("你已经激活过拉，请不要重复点击!");
                            pd.setEditGui(false);
                        }
                    }
                    break;
            }
        }else if(inventory.getTitle().equals(EditInventory.TITLE)){
            if( (RawSlot%9>5 && RawSlot/9!=5) || (RawSlot%9!=5 && RawSlot/9>5)){
                inventoryClickEvent.setCancelled(true);
                player.sendMessage("请不要点击其他的格子");
                return;
            }
            Main.giftPackageList.setItemStack(RawSlot,null);
            Main.rawSlot = RawSlot;
            player.closeInventory();
            Inventory i = Bukkit.createInventory(null,54,"奖励箱子设置");
            i.setContents(Main.confirmUtil.getGiftItemMap().get(RawSlot));
            player.openInventory(i);
        }
//            player.closeInventory();
//            player.openInventory(inventory);
            /**
             * 判断是否是正确的背包
             * 正确则取消事件并且关闭再打开玩家的背包
             */
    }

    /**
     *关闭界面后 刷新GUI 目的是更新GUI来达到更新已获取奖励的目的
     * @param player 玩家目的
     * @param inventory GUI
     * i*9+isSlost = 格子
     */
    public void refresh(Player player,Inventory inventory,PlayData playData){
        player.closeInventory();
        RefreshInventory refreshInventory = new RefreshInventory(inventory);
        inventory.setContents(refreshInventory.refresh(playData));
        player.openInventory(inventory);
    }

    public void refreshKit(PlayData playData,Player player,int i,int j,int RawSlot){
        IsSoltLink isSoltLink = new IsSoltLink(playData.getGuiList(), i, j);
        int isSlost = isSoltLink.IsA();
        if (isSlost != -1) {
            if (!playData.getGuiList(i, isSlost)) {
                player.sendMessage("恭喜完成");
                Main.giftPackageList.getItemStack(RawSlot);
                playData.setGuiList(i,isSlost,true);
            } else {
                player.sendMessage("领取过");
            }
        }
        isSlost = isSoltLink.IsB();
        if (isSlost != -1) {
            if (!playData.getGuiList(isSlost, j)) {
                player.sendMessage("恭喜完成");
                Main.giftPackageList.getItemStack(RawSlot);
                playData.setGuiList(isSlost,j,true);
            } else {
                player.sendMessage("领取过");
            }
        }
    }

    public ItemStack refreshLore(int number){
        ItemStack itemStack = new ItemStack(Material.PURPUR_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = new ArrayList<>();
        list.clear();
        list.add("");
        list.add("您当前积分剩余");
        list.add(number+"");
        list.add("当积分满100时即可进行");
        list.add("指向解密。");
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        itemStack.setType(Main.confirmUtil.getGui()[53].getType());
        return itemStack;
    }
}
