package net.cloud.library.inventories.interfaces;

import net.cloud.library.files.FileUtils;
import net.cloud.library.files.enums.LibraryFileType;
import net.cloud.library.inventories.CloudInventoryBuilder;
import net.cloud.library.inventories.CloudInventoryHolder;
import net.cloud.library.inventories.CloudInventoryItem;
import net.cloud.library.inventories.click.CloudInventoryItemType;
import net.cloud.library.inventories.utils.InventoryUtils;
import net.cloud.library.items.ItemBuilder;
import net.cloud.library.support.ReflectionUtils;
import net.cloud.library.support.XMaterial;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.fusesource.hawtjni.runtime.Library;

import java.util.*;

public class ConfigMenu extends CloudInventoryBuilder {

    @Override
    public CloudInventoryHolder getInventoryHolder() {
        return new CloudInventoryHolder(this);
    }

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public String getTitle() {
        return FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getString("Inventories." + getName() + ".Settings.Title", " Title Error");
    }

    @Override
    public Integer getSize() {
        return FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getInt("Inventories." + getName() + ".Settings.Size", 54);
    }

    @Override
    public Map<Integer, CloudInventoryItem> getItems() {
        Map<Integer, CloudInventoryItem> items = new HashMap<>();
        FileConfiguration config = FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG);

        if(!(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).contains("Inventories." + getName() + ".Items"))) return items;
        for(String item : FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getConfigurationSection("Inventories." + getName() + ".Items").getKeys(false)) {
            String path = "Inventories." + getName() + ".Items." + item + ".";

            ItemBuilder itemBuilder = new ItemBuilder(XMaterial.matchXMaterial(config.getString(path + ".Display-Item.Material", "DIAMOND:0")).get().parseItem(), 1)
                    .setEnchanted(config.getBoolean(path + ".Display-Item.Enchanted", false))
                    .setFlagsHidden(!(config.getBoolean(path + ".Display-Item.Show-Item-Flags", false)))
                    .setName(config.getString(path + ".Display-Item.Name"))
                    .setLore(config.getStringList(path + ".Display-Item.Lore"));

            CloudInventoryItem iItem = new CloudInventoryItem(item, itemBuilder.getItem(), new CloudInventoryItemType() {
                @Override
                public void onClick(Player player, Inventory inventory, Integer slot) {
                    player.sendMessage("Clicked: " + item + " | Slot: " + slot);
                    player.openInventory(new ExampleMenu().getInventory());
                }
            });

            Iterator<Integer> slots = InventoryUtils.getUtils().getSlots(config, path + ".Slots").iterator();
            if(slots == null) continue;

            while(slots.hasNext()) {
                items.put(slots.next(), iItem);
            }
        }
        return items;
    }
}
