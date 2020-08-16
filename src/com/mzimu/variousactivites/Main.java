package com.mzimu.variousactivites;

import com.mzimu.variousactivites.decryptconfirg.ConfirmUtil;
import com.mzimu.variousactivites.decryptkey.DecryptMain;
import com.mzimu.variousactivites.decryptkey.event.GiftPackageList;
import com.mzimu.variousactivites.decryptkey.data.PlayData;
import com.mzimu.variousactivites.decryptkey.trigger.DecryptInventoryClick;
import com.mzimu.variousactivites.decryptkey.trigger.DecryptInventoryClose;
import com.mzimu.variousactivites.decryptkey.trigger.DecryptPlayerJoin;
import com.mzimu.variousactivites.decryptkey.trigger.DecryptPlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main extends JavaPlugin {
    public static Inventory decryptInventory = Bukkit.createInventory(null,54,DecryptMain.InventoryTitle);
    public static Map<String, PlayData> playDataMap = new HashMap<>();
    public static ConfirmUtil confirmUtil;
    public static GiftPackageList giftPackageList;
    public static int rawSlot=0;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        confirmUtil = new ConfirmUtil(this);
        giftPackageList = new GiftPackageList(confirmUtil.getGiftItemMap());
        decryptInventory.setContents(confirmUtil.getGui());

        new DecryptInventoryClose(this);
        new DecryptInventoryClick(this);
        new DecryptPlayerJoin(this);
        new DecryptPlayerQuit(this);

        getCommand("zmva").setExecutor(new CommandMain());
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Iterator iterator = getServer().getOnlinePlayers().iterator();
        while(iterator.hasNext()){
            Player player = (Player) iterator.next();
            confirmUtil.savePlayGui(player.getName(),playDataMap.get(player.getName()).getGuiList());
        }
        super.onDisable();
    }
}
