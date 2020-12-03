package net.cloud.library;

import net.cloud.library.files.FileUtils;
import net.cloud.library.inventories.CloudInventoryHolder;
import net.cloud.library.inventories.click.CloudInventoryItemType;
import net.cloud.library.inventories.interfaces.ConfigMenu;
import net.cloud.library.support.ReflectionUtils;
import net.cloud.library.support.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudLibrary extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        library = this;
        FileUtils.getInstance().init();
        ReflectionUtils.getUtils();
        getLogger().info("CloudLibrary - A library full of developer needs");

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.openInventory(new ConfigMenu().getInventory());
        }

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = (Inventory) player.getOpenInventory().getTopInventory();
        if(inventory.getHolder() instanceof CloudInventoryHolder) {
            CloudInventoryHolder holder = (CloudInventoryHolder) event.getInventory().getHolder();

            event.setCancelled(true);

            if(event.getRawSlot() < holder.getBuilder().getSize()) {
                CloudInventoryItemType type = holder.getBuilder().getItems().get(event.getSlot()).getType();
                if(type == null) return;

                type.onClick(player, inventory, event.getSlot());
            }
        }
    }

    private static CloudLibrary library;
    public static CloudLibrary getLibrary() {
        return library;
    }

}
