package com.myass.buttplugin.listeners;

import com.myass.buttplugin.enchants.Vampirism;
import com.myass.buttplugin.helpers.EnchantManager;
import com.myass.buttplugin.models.CustomEnchantInstance;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantListener implements Listener {

  @EventHandler
  public void onEnchantEvent(EnchantItemEvent event) {
    ItemStack item = event.getItem();

    // TODO: Add logic to add custom enchants with a chance based on weight.
    EnchantManager.addCustomEnchantToItem(new CustomEnchantInstance(1, new Vampirism()), item);
  }

}
