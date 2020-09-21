package com.myass.buttplugin.models;

public class CustomEnchantInstance {
  private int level;
  private CustomEnchant enchant;

  public CustomEnchantInstance(int level, CustomEnchant enchant) {
    this.level = level;
    this.enchant = enchant;
  }
  
  public int getLevel() {
    return level;
  }
  public CustomEnchant getEnchant() {
    return enchant;
  }
}
