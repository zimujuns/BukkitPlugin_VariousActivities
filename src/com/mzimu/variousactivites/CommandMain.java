package com.mzimu.variousactivites;

import com.mzimu.variousactivites.decryptkey.DecryptMain;
import com.mzimu.variousactivites.decryptkey.event.GiftPackageList;
import com.mzimu.variousactivites.decryptkey.data.PlayData;
import com.mzimu.variousactivites.decryptkey.event.EditInventory;
import com.mzimu.variousactivites.decryptkey.event.RefreshInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandMain implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = ((Player) commandSender).getPlayer();
            if(strings.length==0){
                player.sendMessage("您的指令有误");
                return false;
            }
            switch (strings[0]){
                case "open":
                    PlayData playData = Main.playDataMap.get(player.getName());
                    Inventory decryptInventory = Bukkit.createInventory(null,54, DecryptMain.InventoryTitle);
                    decryptInventory.setContents(Main.decryptInventory.getContents());
                    RefreshInventory refreshInventory = new RefreshInventory(decryptInventory);
                    ItemStack[] itemStacks = refreshInventory.refresh(playData);
                    decryptInventory.setContents(itemStacks);
                    player.openInventory(decryptInventory);
                    break;
                case "edit":
                    if(strings.length==2){
                        EditInventory editInventory = new EditInventory();
                        editInventory.edit(player, Boolean.parseBoolean(strings[1]));
                        break;
                    }
                default:
                    player.sendMessage("您输入的指令错误，请重试");
                    return false;
            }
            return true;
        }

        return false;
    }
}
