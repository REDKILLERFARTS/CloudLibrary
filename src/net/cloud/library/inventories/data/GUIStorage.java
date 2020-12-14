package net.cloud.library.inventories.data;

import net.cloud.library.files.FileUtils;
import net.cloud.library.files.enums.LibraryFileType;
import net.cloud.library.inventories.CloudInventoryBuilder;
import net.cloud.library.inventories.interfaces.ConfigMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.fusesource.hawtjni.runtime.Library;

import java.util.HashMap;
import java.util.Map;

public class GUIStorage {

    private Map<String, CloudInventoryBuilder> guis = new HashMap<>(); //Name -> GUI

    public void addGUI(CloudInventoryBuilder builder) {
        guis.put(builder.getName().toLowerCase(), builder);
    }

    public void removeGUI(CloudInventoryBuilder builder) {
        try {
            guis.remove(builder.getName().toLowerCase());
        } catch(Exception e) {  }
    }

    public void removeGUI(String name) {
        try {
            guis.remove(name.toLowerCase());
        } catch(Exception e) {  }
    }

    public boolean hasGUI(CloudInventoryBuilder builder) {
        try {
            if(guis.containsKey(builder.getName().toLowerCase())) {
                return true;
            }
            return false;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean hasGUI(String name) {
        try {
            if(guis.containsKey(name.toLowerCase())) {
                return true;
            }
            return false;
        } catch(Exception e) {
            return false;
        }
    }

    public CloudInventoryBuilder getGUIByName(String name) {
        try {
            if(hasGUI(name)) {
                CloudInventoryBuilder builder = guis.get(name.toLowerCase());
                return builder;
            }
        } catch(Exception e) {  }
        return null;
    }

    public CloudInventoryBuilder getFirstGUI() {
        try {
            CloudInventoryBuilder builder = guis.get(guis.keySet().toArray()[0]);
            return builder;
        } catch(Exception e) {  }
        return null;
    }

    public void load() {
        if(!(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).contains("Inventories"))) return;
        if(!(FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).isConfigurationSection("Inventories"))) return;
        for(String inventory : FileUtils.getInstance().getFileByType(LibraryFileType.CONFIG).getConfigurationSection("Inventories").getKeys(false)) {
            addGUI(new ConfigMenu(inventory));
        }
    }

    public GUIStorage() {
        load();
    }

    private static GUIStorage storage;
    public static GUIStorage getStorage() {
        if(storage == null) storage = new GUIStorage();
        return storage;
    }
}
