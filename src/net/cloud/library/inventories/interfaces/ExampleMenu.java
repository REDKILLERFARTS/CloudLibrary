package net.cloud.library.inventories.interfaces;

import net.cloud.library.inventories.CloudInventoryBuilder;
import net.cloud.library.inventories.CloudInventoryItem;
import net.cloud.library.inventories.click.CloudInventoryItemType;
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
                .setName("&4&l[!] &eDisplay Item")
                .setLore(Arrays.asList("Test", "Test2", "&cTest3"))
                .setEnchanted(true);

        CloudInventoryItem border = new CloudInventoryItem("Border", item.getItem(), new CloudInventoryItemType() {
            @Override
            public void onClick(Player player, Inventory inventory, Integer slot) {
                player.sendMessage(ReflectionUtils.getUtils().getColor("&6&l[!] &eYou have clicked the &a" + slot + " &eslot."));
            }
        });

        for(int i=0; i < getSize(); i++) {
            items.put(i, border);
        }

        CloudInventoryItem newBorder = new CloudInventoryItem("Border", XMaterial.matchXMaterial("STAINED_GLASS_PANE:5").get().parseItem(), new CloudInventoryItemType() {
            @Override
            public void onClick(Player player, Inventory inventory, Integer slot) {
                player.sendMessage(ReflectionUtils.getUtils().getColor("&4&l[!] &cYou clicked the special slot. &8(&7" + slot + "&8)"));
            }
        });
        items.put(0, newBorder);
        return items;
    }
}
