package net.cloud.library.inventories.interfaces;

import net.cloud.library.inventories.CloudInventoryBuilder;
import net.cloud.library.inventories.CloudInventoryItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ExampleMenu extends CloudInventoryBuilder {

    @Override
    public String getName() {
        return "Example Menu";
    }

    @Override
    public String getTitle() {
        return "&lEXAMPLE MENU";
    }

    @Override
    public Integer getSize() {
        return 54;
    }

    @Override
    public Map<Integer, CloudInventoryItem> getItems() {
        Map<Integer, CloudInventoryItem> items = new HashMap<>();
        CloudInventoryItem border = new CloudInventoryItem("Border", new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));

        for(int i=0; i < getSize() - 9; i++) {
            items.put(i, border);
        }
        return items;
    }
}
