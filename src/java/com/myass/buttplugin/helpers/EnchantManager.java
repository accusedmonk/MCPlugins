package com.myass.buttplugin.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.myass.buttplugin.ButtPlugin;
import com.myass.buttplugin.models.CustomEnchant;
import com.myass.buttplugin.models.CustomEnchantInstance;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EnchantManager {

    private static HashMap<String, CustomEnchant> customEnchants = new HashMap<>();

    public static void registerCustomEnchant(CustomEnchant customEnchant) {
        customEnchants.put(customEnchant.getId(), customEnchant);
    }

    public static CustomEnchant getCustomEnchant(String id) {
        return customEnchants.get(id);
    }

    public static List<CustomEnchantInstance> getEntityEnchants(LivingEntity entity) {
        EntityEquipment equipment = entity.getEquipment();

        // Get list of items equipped
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(equipment.getItemInMainHand());
        items.add(equipment.getItemInOffHand());
        for (ItemStack item : equipment.getArmorContents()) {
            items.add(item);
        }

        // Iterate over to get the highest effective level of each enchant
        HashMap<String, Integer> customEnchantLevels = new HashMap<>();
        for (ItemStack item : items) {
            if (item != null && item.hasItemMeta()) {
                ItemMeta itemMeta = item.getItemMeta();
                PersistentDataContainer persistentContainer = itemMeta.getPersistentDataContainer();
                NamespacedKey customKey = new NamespacedKey(ButtPlugin.getInstance(), "Enchants");

                if (!persistentContainer.has(customKey, PersistentDataType.STRING)) {
                    continue;
                }

                Gson gson = new Gson();
                String jsonEnchants = persistentContainer.get(customKey, PersistentDataType.STRING);
                HashMap<String, Double> customEnchantMap = gson.fromJson(jsonEnchants, HashMap.class);

                for (Entry<String, Double> entry : customEnchantMap.entrySet()) {
                    CustomEnchant cEnchant = getCustomEnchant(entry.getKey());
                    Integer level = (int) Math.round(entry.getValue());
                    if (customEnchantLevels.containsKey(cEnchant.getId())) {
                        int currentLevel = customEnchantLevels.get(cEnchant.getId());

                        if (level > currentLevel) {
                            customEnchantLevels.put(cEnchant.getId(), level);
                        }
                    } else {
                        customEnchantLevels.put(cEnchant.getId(), level);
                    }
                }
            }
        }

        // Flatten enchants to EnchantInstances
        ArrayList<CustomEnchantInstance> equippedEnchants = new ArrayList<>();
        for (Entry<String, Integer> entry : customEnchantLevels.entrySet()) {
            equippedEnchants.add(new CustomEnchantInstance(entry.getValue(), customEnchants.get(entry.getKey())));
        }

        return equippedEnchants;
    }

    public static void addCustomEnchantToItem(CustomEnchantInstance customEnchantInstance, ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();

        NamespacedKey customKey = new NamespacedKey(ButtPlugin.getInstance(), "Enchants");
        Gson gson = new Gson();
        HashMap<String, Integer> customEnchantMap = null;
        PersistentDataContainer persistentContainer = itemMeta.getPersistentDataContainer();

        if (persistentContainer.has(customKey, PersistentDataType.STRING)) {
            customEnchantMap = gson.fromJson(persistentContainer.get(customKey, PersistentDataType.STRING),
                    HashMap.class);
        } else {
            customEnchantMap = new HashMap<String, Integer>();
        }

        if (customEnchantMap.size() == 0) {
            customEnchantMap.put(customEnchantInstance.getEnchant().getId(), customEnchantInstance.getLevel());
            lore.add(customEnchantInstance.getLoreLine());
        } else {
            for (Entry<String, Integer> entry : customEnchantMap.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(customEnchantInstance.getEnchant().getId())) {
                    int currentLevel = entry.getValue();
                    int newLevel = customEnchantInstance.getLevel();

                    CustomEnchantInstance cei = new CustomEnchantInstance(currentLevel,
                            getCustomEnchant(entry.getKey()));
                    int loreLine = lore.indexOf(cei.getLoreLine());

                    if (newLevel > currentLevel) {
                        cei.setLevel(newLevel);
                    } else if (newLevel == currentLevel) {
                        cei.setLevel(currentLevel + 1);
                    }

                    lore.set(loreLine, cei.getLoreLine());
                }
            }
        }

        String jsonEnchants = gson.toJson(customEnchantMap);
        persistentContainer.set(customKey, PersistentDataType.STRING, jsonEnchants);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
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