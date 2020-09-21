package com.myass.buttplugin.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantListener implements Listener{
  @EventHandler
  public void onEnchantEvent (EnchantItemEvent event) {
      Player player = event.getEnchanter();
      ItemStack item = event.getItem();
      Material mat = item.getType();

      System.out.println(player.getName()+" enchanted a "+mat.name());
  }
}
