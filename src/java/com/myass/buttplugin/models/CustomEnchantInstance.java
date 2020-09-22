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

  public void setLevel(int level) {
    this.level = level;
  }

  public String getLoreLine() {
    return this.enchant.getLoreLine(level);
  }

  public CustomEnchant getEnchant() {
    return enchant;
  }
}
