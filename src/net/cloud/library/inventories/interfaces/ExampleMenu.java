package net.cloud.library.inventories.interfaces;

import net.cloud.library.inventories.CloudInventoryBuilder;
import net.cloud.library.inventories.CloudInventoryHolder;
import net.cloud.library.inventories.CloudInventoryItem;
import net.cloud.library.inventories.click.CloudInventoryItemType;
import net.cloud.library.inventories.data.GUIStorage;
import net.cloud.library.items.ItemBuilder;
import net.cloud.library.support.ReflectionUtils;
import net.cloud.library.support.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExampleMenu extends CloudInventoryBuilder {

    @Override
    public CloudInventoryHolder getInventoryHolder() {
        return new CloudInventoryHolder(this, new ConfigMenu("Default"));
    }

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

        ItemBuilder item = new ItemBuilder(XMaterial.matchXMaterial("STAINED_GLASS_PANE:15").get().parseItem(), 1)
                .setName(" ")
                .setLore(Arrays.asList(" "))
                .setEnchanted(false)
                .setFlagsHidden(true);

        CloudInventoryItem border = new CloudInventoryItem("Border", item.getItem(), new CloudInventoryItemType() {
            @Override
            public void onClick(Player player, Inventory inventory, Integer slot) {
                player.sendMessage(ReflectionUtils.getUtils().getColor("&6&l[!] &eYou have clicked the &a" + slot + " &eslot."));
            }
        });

        for(int i=0; i < getSize(); i++) {
            items.put(i, border);
        }
        return items;
    }

}
