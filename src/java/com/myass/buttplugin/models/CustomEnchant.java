package com.myass.buttplugin.models;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomEnchant {
  private String displayName;
  private String id;
  private double weight;
  private int maxLevel;
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

  public boolean canEnchant(ItemStack items) {
    return this.itemTypes.contains(items.getType());
  }

  public String getDisplayName() {
    return displayName;
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
