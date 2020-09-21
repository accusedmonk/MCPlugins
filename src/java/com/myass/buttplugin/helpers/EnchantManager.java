package com.myass.buttplugin.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.myass.buttplugin.models.CustomEnchant;
import com.myass.buttplugin.models.CustomEnchantInstance;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantManager {

    private static HashMap<String, CustomEnchant> customEnchants = new HashMap<>();

    public static void registerCustomEnchant(CustomEnchant customEnchant) {
        customEnchants.put(customEnchant.getId(), customEnchant);
        System.out.println("Registered Custom Enchant: " + customEnchant.getDisplayName());
    }

    public static List<CustomEnchantInstance> getEntityEnchants(LivingEntity entity) {
        EntityEquipment equipment = entity.getEquipment();

        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(equipment.getItemInMainHand());
        items.add(equipment.getItemInOffHand());
        for (ItemStack item : equipment.getArmorContents()) {
            items.add(item);
        }

        HashMap<String, Integer> customEnchantLevels = new HashMap<>();
        for (ItemStack item : items) {
            if(item != null){
                // TODO: Get from item
            }
        }

        ArrayList<CustomEnchantInstance> equippedEnchants = new ArrayList<>();
        for(Entry<String, Integer> entry : customEnchantLevels.entrySet()){
            equippedEnchants.add(new CustomEnchantInstance(entry.getValue(), customEnchants.get(entry.getKey()));
        }
    }

    public static void addCustomEnchantToItem(CustomEnchantInstance customEnchantInstance, ItemStack item) {
        if (!item.hasItemMeta()) {
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();
        String enchantName = ChatColor.GRAY + customEnchantInstance.getEnchant().getDisplayName();
        String romanNumeral = toRoman(customEnchantInstance.getLevel());
        String enchantLine = enchantName + " " + romanNumeral;

        // TODO:
        // Remove lower or equal level of the same enchant if it exists
        // - And remove from Lore
        // Add enchantLine to lore
    }

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public final static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number - l);
    }

}