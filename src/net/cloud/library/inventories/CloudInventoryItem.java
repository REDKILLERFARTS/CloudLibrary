package net.cloud.library.inventories;

import org.bukkit.inventory.ItemStack;

public class CloudInventoryItem {

    public String name;
    public ItemStack item;

    public CloudInventoryItem(String name, ItemStack item) {
        setName(name);
        setItem(item);
    }

    public String getName() {
        return name;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

}
