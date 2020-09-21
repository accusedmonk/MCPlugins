package com.myass.buttplugin.enchants;

import java.util.ArrayList;
import java.util.Random;

import com.myass.buttplugin.models.CombatEnchant;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

public class Vampirism extends CombatEnchant {
  private Random random = new Random();

	public Vampirism() {
		super("Vampirism", "vampirism", 10.0, 3, new ArrayList<Material>(){{
      add(Material.WOODEN_SWORD);
      add(Material.STONE_SWORD);
      add(Material.IRON_SWORD);
      add(Material.GOLDEN_SWORD);
      add(Material.DIAMOND_SWORD);
      add(Material.NETHERITE_SWORD);
    }});
	}

	@Override
	public void onAttack(int level, LivingEntity attacker, LivingEntity defender) {
    double roll = random.nextDouble() * 100;

    if (roll <= 35.0){
      int heal = random.nextInt(level) + 1;

      attacker.setHealth(Math.min(attacker.getHealth()+heal, attacker.getMaxHealth()));
    }
	}

	@Override
	public void onDefend(int level, LivingEntity attacker, LivingEntity defender) {
				
	}
  
}
