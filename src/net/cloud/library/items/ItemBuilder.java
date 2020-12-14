package net.cloud.library.items;

import net.cloud.library.support.ReflectionUtils;
import net.cloud.library.support.XMaterial;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    ItemStack item;

    public ItemBuilder(ItemStack item, Integer amount) {
        this.item = item;
        this.item.setAmount(amount);
    }

    public ItemBuilder(Material material, Integer amount, short data) {
        item = new ItemStack(material, amount, data);
    }

    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ReflectionUtils.getUtils().getColor(name));
        item.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        List<String> lores = new ArrayList<>();
        for(String line : lore) {
            lores.add(ReflectionUtils.getUtils().getColor(line));
        }

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lores);
        item.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder setEnchanted(boolean enchanted) {
        if(enchanted) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(itemMeta);
        } else {
            if(item.getEnchantments() == null || item.getEnchantments().isEmpty()) return this;
            for(Enchantment enchant : item.getEnchantments().keySet()) {
                item.removeEnchantment(enchant);
            }
        }

        return this;
    }

    public ItemBuilder setFlagsHidden(boolean flagsHidden) {
        ItemMeta itemMeta = item.getItemMeta();
        if(flagsHidden) {
            for(ItemFlag flag : ItemFlag.values()) {
                itemMeta.addItemFlags(flag);
            }
        } else {
            for(ItemFlag flag : ItemFlag.values()) {
                try {
                    itemMeta.removeItemFlags(flag);
                } catch(Exception e) {  continue;   }
            }
        }
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack getItem() {
        return item;
    }

}
