package net.cloud.library.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public abstract class CloudInventoryBuilder {

    public abstract String getName();
    public abstract String getTitle();
    public abstract Integer getSize();
    public abstract Map<Integer, CloudInventoryItem> getItems();

    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(new CloudInventoryHolder(this), getSize(), getTitle());

        Map<Integer, CloudInventoryItem> items = getItems();
        for (Integer slot : items.keySet()) {
            inventory.setItem(slot, items.get(slot).getItem());
        }

        return inventory;
    }
}
