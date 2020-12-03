package net.cloud.library.inventories.click;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class CloudInventoryItemType {

    public abstract void onClick(Player player, Inventory inventory, Integer slot);


}
