package com.myass.buttplugin.listeners;

import java.util.List;
import java.util.Random;

import com.myass.buttplugin.helpers.EnchantManager;
import com.myass.buttplugin.models.CustomEnchant;
import com.myass.buttplugin.models.CustomEnchantInstance;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantListener implements Listener {

  private Random random = new Random();

  @EventHandler
  public void onEnchantEvent(EnchantItemEvent event) {
    ItemStack item = event.getItem();

    addCustomEnchant(event.getExpLevelCost(), item);
  }

  @EventHandler
  public void onAnvilEvent(PrepareAnvilEvent event) {
    if (event.getResult() == null || event.getResult().getType() == Material.AIR) {
      return;
    }

    ItemStack[] contents = event.getInventory().getContents();
    ItemStack result = event.getResult();
    List<CustomEnchantInstance> sacrificeEnchants = EnchantManager.getCustomEnchantsFromItem(contents[1]);

    for (CustomEnchantInstance cei : sacrificeEnchants) {
      EnchantManager.addCustomEnchantToItem(cei, result);
    }

    event.setResult(result);
  }

  private void addCustomEnchant(int tier, ItemStack item) {
    if (tier == 1) {
      return;
    }

    // 20% chance at tier 2 enchant, 30% chance at tier 3 enchant.
    double chance = (tier == 2 ? 20 : 30);
    double roll = random.nextDouble() * 100;
    if (roll > chance) {
      return;
    }

    List<CustomEnchant> customEnchants = EnchantManager.getCustomEnchantsForMaterial(item.getType());
    if (customEnchants == null || customEnchants.size() == 0) {
      return;
    }

    // TODO: Leverage enchants.weight to provide different chances.
    int randIndex = random.nextInt(customEnchants.size());
    CustomEnchant enchant = customEnchants.get(randIndex);
    CustomEnchantInstance enchantInstance = new CustomEnchantInstance(1, enchant);
    EnchantManager.addCustomEnchantToItem(enchantInstance, item);
  }

}
