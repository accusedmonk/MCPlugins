package com.myass.buttplugin.models;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public abstract class CombatEnchant extends CustomEnchant {

  public CombatEnchant(String displayName, double weight, int maxLevel, List<Material> itemTypes) {
    super(displayName, weight, maxLevel, itemTypes);
  }

  public abstract void onAttack(EntityDamageByEntityEvent event, int level, LivingEntity attacker, Entity defender);

  public abstract void onDefend(EntityDamageByEntityEvent event, int level, Entity attacker, LivingEntity defender);

}
