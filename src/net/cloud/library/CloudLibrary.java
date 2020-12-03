package net.cloud.library;

import net.cloud.library.inventories.CloudInventoryHolder;
import net.cloud.library.inventories.interfaces.ExampleMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CloudLibrary extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("CloudLibrary - A library full of developer needs");

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.openInventory(new ExampleMenu().getInventory());
        }

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getInventory().getHolder() instanceof CloudInventoryHolder) {
            CloudInventoryHolder holder = (CloudInventoryHolder) event.getInventory().getHolder();

            event.setCancelled(true);
            event.getWhoClicked().sendMessage(holder.getBuilder().getName());
        }
    }

}
