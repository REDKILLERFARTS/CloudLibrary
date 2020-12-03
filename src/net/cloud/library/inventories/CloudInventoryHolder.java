package net.cloud.library.inventories;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CloudInventoryHolder implements InventoryHolder {

    private CloudInventoryBuilder builder;

    public CloudInventoryHolder(CloudInventoryBuilder builder) {
        setBuilder(builder);
    }

    public CloudInventoryBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(CloudInventoryBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
