package com.myass.buttplugin.models;

import java.util.List;

import com.myass.buttplugin.helpers.EnchantManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomEnchant {
  private String displayName;
  private String id;
  private double weight;
  private int maxLevel;
  private ChatColor enchantTextColor = ChatColor.GRAY;
  private List<Material> itemTypes;

  public CustomEnchant(String displayName, String id, double weight, int maxLevel, List<Material> itemTypes) {
    this.displayName = displayName;
    this.id = id;
    this.weight = weight;
    this.maxLevel = maxLevel;

    // All Enchantments should be able to go on books.
    itemTypes.add(Material.BOOK);
    this.itemTypes = itemTypes;
  }

  public CustomEnchant(String displayName, String id, double weight, int maxLevel, List<Material> itemTypes,
      ChatColor enchantTextColor) {
    this(displayName, id, weight, maxLevel, itemTypes);

    this.enchantTextColor = enchantTextColor;
  }

  public boolean canEnchant(ItemStack items) {
    return this.itemTypes.contains(items.getType());
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getLoreLine(int level) {
    return enchantTextColor + displayName + " " + EnchantManager.toRoman(level);
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public int getMaxLevel() {
    return maxLevel;
  }

  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }

  public List<Material> getItemTypes() {
    return itemTypes;
  }

  public void setItemTypes(List<Material> itemTypes) {
    this.itemTypes = itemTypes;
  }

}
