package net.cloud.library.inventories;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CloudInventoryHolder implements InventoryHolder {

    private CloudInventoryBuilder builder;
    private CloudInventoryBuilder closeInventory;

    public CloudInventoryHolder(CloudInventoryBuilder builder) {
        setBuilder(builder);
    }
    public CloudInventoryHolder(CloudInventoryBuilder builder, CloudInventoryBuilder closeInventory){
        setBuilder(builder);
        setCloseInventory(closeInventory);
    }

    public CloudInventoryBuilder getBuilder() {
        return builder;
    }
    public CloudInventoryBuilder getCloseInventoryBuilder() {
        return closeInventory;
    }

    public void setBuilder(CloudInventoryBuilder builder) {
        this.builder = builder;
    }
    public void setCloseInventory(CloudInventoryBuilder closeInventory) {
        this.closeInventory = closeInventory;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
