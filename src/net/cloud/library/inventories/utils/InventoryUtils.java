package net.cloud.library.inventories.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryUtils {

    public List<Integer> getSlots(FileConfiguration file, String path) {
        if(!(file.contains(path))) return null;
        if(file.isList(path)) {
            return getSlots(file.getStringList(path));
        } else {
            if(isInteger(file.getString(path))) {
                return Arrays.asList(file.getInt(path));
            }
        }
        return null;
    }

    public List<Integer> getSlots(List<String> slots) {
        List<Integer> array = new ArrayList<>();
        for(String slotString : slots) {
            if(slotString.contains("-")){
                String[] slotSplit = slotString.split("-");

                if(isInteger(slotSplit[0]) && isInteger(slotSplit[1])) {
                    Integer min = Integer.valueOf(slotSplit[0]);
                    Integer max = Integer.valueOf(slotSplit[1]);

                    if(min > max) continue;
                    for(int i=min; i <= max; i++) {
                        array.add(i);
                    }
                }
                continue;
            } else {
                if(isInteger(slotString)) {
                    array.add(Integer.valueOf(slotString));
                    continue;
                }
            }
        }
        return array;
    }

    public boolean isInteger(String args) {
        try {
            Integer.parseInt(args);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private static InventoryUtils utils;
    public static InventoryUtils getUtils(){
        if(utils == null) utils = new InventoryUtils();
        return utils;
    }
}
