package com.myass.buttplugin.models;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public abstract class CombatEnchant extends CustomEnchant {

  public CombatEnchant(String displayName, String id, double weight, int maxLevel, List<Material> itemTypes) {
    super(displayName, id, weight, maxLevel, itemTypes);
  }

  public abstract void onAttack(int level, LivingEntity attacker, Entity defender);

  public abstract void onDefend(int level, Entity attacker, LivingEntity defender);

}
