package net.cloud.library.inventories.interfaces;

import net.cloud.library.files.FileUtils;
import net.cloud.library.files.enums.LibraryFileType;
import net.cloud.library.inventories.CloudInventoryBuilder;
import net.cloud.library.inventories.CloudInventoryItem;
import net.cloud.library.inventories.click.CloudInventoryItemType;
import net.cloud.library.items.ItemBuilder;
import net.cloud.library.support.ReflectionUtils;
import net.cloud.library.support.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.fusesource.hawtjni.runtime.Library;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigMenu extends CloudInventoryBuilder {

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public String getTitle() {
        return FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getString("Default.Title", "Broken Title");
    }

    @Override
    public Integer getSize() {
        return FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getInt("Default.Size", 54);
    }

    @Override
    public Map<Integer, CloudInventoryItem> getItems() {
        Map<Integer, CloudInventoryItem> items = new HashMap<>();

        if(!(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).contains("Default.Items"))) return items;
        for(String item : FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getConfigurationSection("Default.Items").getKeys(false)) {
            ItemBuilder itemStack = new ItemBuilder(XMaterial.matchXMaterial(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getString("Default.Items." + item + ".Material", "STAINED_GLASS_PANE:15")).get().parseItem(), 1)
                    .setEnchanted(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getBoolean("Default.Items." + item + ".Enchanted", false))
                    .setName(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getString("Default.Items." + item + ".Name", "Display Name Error"))
                    .setLore(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getStringList("Default.Items." + item + ".Lore"));

            CloudInventoryItem iItem = new CloudInventoryItem(item, itemStack.getItem(), new CloudInventoryItemType() {
                @Override
                public void onClick(Player player, Inventory inventory, Integer slot) {
                    player.sendMessage("Clicked: " + item + " | Slot: " + slot);
                }
            });

            if(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).isList("Default.Items." + item + ".Slots")) {
                try {
                    List<Integer> slots = FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getIntegerList("Default.Items." + item + ".Slots");
                    for(Integer slot : slots) {
                        items.put(slot, iItem);
                    }
                } catch(Exception e) {
                    continue;
                }
            } else {
                try {
                     items.put(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getInt("Default.Items." + item + ".Slots", 0), iItem);
                } catch(Exception e) {
                    continue;
                }
            }
        }
        return items;
    }
}
