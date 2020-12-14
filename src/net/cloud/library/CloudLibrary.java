package net.cloud.library;

import net.cloud.library.files.FileUtils;
import net.cloud.library.inventories.data.GUIStorage;
import net.cloud.library.inventories.events.InventoryFunctions;
import net.cloud.library.support.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudLibrary extends JavaPlugin {

    @Override
    public void onEnable() {
        library = this;
        FileUtils.getInstance().init();
        ReflectionUtils.getUtils();
        GUIStorage.getStorage().load();
        getLogger().info("CloudLibrary - A library full of developer needs");

//        for (Player player : Bukkit.getOnlinePlayers()) {
//            GUIStorage.getStorage().getFirstGUI().openInventory(player);
//        }

        getServer().getPluginManager().registerEvents(new InventoryFunctions(), this);
    }

    private static CloudLibrary library;
    public static CloudLibrary getLibrary() {
        return library;
    }

}
