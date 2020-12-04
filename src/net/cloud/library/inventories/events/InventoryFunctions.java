package net.cloud.library.inventories.events;

import net.cloud.library.CloudLibrary;
import net.cloud.library.inventories.CloudInventoryHolder;
import net.cloud.library.inventories.click.CloudInventoryItemType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryFunctions implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = (Inventory) player.getOpenInventory().getTopInventory();
        if(inventory.getHolder() instanceof CloudInventoryHolder) {
            CloudInventoryHolder holder = (CloudInventoryHolder) event.getInventory().getHolder();

            event.setCancelled(true);

            if(event.getRawSlot() < holder.getBuilder().getSize()) {
                CloudInventoryItemType type = holder.getBuilder().getItems().get(event.getSlot()).getType();
                if(type == null) return;

                type.onClick(player, inventory, event.getSlot());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = (Inventory) event.getInventory();
        if(inventory.getHolder() instanceof CloudInventoryHolder) {
            CloudInventoryHolder holder = (CloudInventoryHolder) event.getInventory().getHolder();
            if(holder.getCloseInventoryBuilder() == null) return;
            if(holder.getOpeningNew()) return;

            Bukkit.getScheduler().scheduleSyncDelayedTask(CloudLibrary.getLibrary(), new Runnable() {
                @Override
                public void run() {
                    event.getPlayer().openInventory(holder.getCloseInventoryBuilder().getInventory());
                }
            }, 2L);
        }
    }

}
