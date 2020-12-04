package net.cloud.library.inventories;

import net.cloud.library.support.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sun.reflect.Reflection;

import java.util.Map;

public abstract class CloudInventoryBuilder {

    public abstract CloudInventoryHolder getInventoryHolder();
    public abstract String getName();
    public abstract String getTitle();
    public abstract Integer getSize();
    public abstract Map<Integer, CloudInventoryItem> getItems();

    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(getInventoryHolder(), getSize(), ReflectionUtils.getUtils().getColor(getTitle()));

        Map<Integer, CloudInventoryItem> items = getItems();
        for (Integer slot : items.keySet()) {
            inventory.setItem(slot, items.get(slot).getItem());
        }

        return inventory;
    }
}
