package net.cloud.library.inventories;

import net.cloud.library.inventories.click.CloudInventoryItemType;
import org.bukkit.inventory.ItemStack;

public class CloudInventoryItem {

    public String name;
    public ItemStack item;
    public CloudInventoryItemType type;

    public CloudInventoryItem(String name, ItemStack item) {
        setName(name);
        setItem(item);
    }

    public CloudInventoryItem(String name, ItemStack item, CloudInventoryItemType type) {
        setName(name);
        setItem(item);
        setType(type);
    }

    public String getName() {
        return name;
    }

    public ItemStack getItem() {
        return item;
    }

    public CloudInventoryItemType getType() {
        return type;
    }

    public CloudInventoryItem setName(String name) {
        this.name = name;
        return this;
    }

    public CloudInventoryItem setItem(ItemStack item) {
        this.item = item;
        return this;
    }

    public CloudInventoryItem setType(CloudInventoryItemType type) {
        this.type = type;
        return this;
    }

}
