package com.myass.buttplugin.enchants;

import java.util.ArrayList;
import java.util.Random;

import com.myass.buttplugin.models.CombatEnchant;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Vampirism extends CombatEnchant {
  private Random random = new Random();

  public Vampirism() {
    super("Vampirism", 10.0, 3, new ArrayList<Material>() {
      {
        add(Material.WOODEN_SWORD);
        add(Material.STONE_SWORD);
        add(Material.IRON_SWORD);
        add(Material.GOLDEN_SWORD);
        add(Material.DIAMOND_SWORD);
        add(Material.NETHERITE_SWORD);
        add(Material.WOODEN_AXE);
        add(Material.STONE_AXE);
        add(Material.IRON_AXE);
        add(Material.GOLDEN_AXE);
        add(Material.DIAMOND_AXE);
        add(Material.NETHERITE_AXE);
      }
    });
  }

  @Override
  public void onAttack(EntityDamageByEntityEvent event, int level, LivingEntity attacker, Entity defender) {
    double roll = random.nextDouble() * 100;

    if (roll <= 15.0) {
      int heal = random.nextInt(level) + 1;

      attacker.setHealth(Math.min(attacker.getHealth() + heal, attacker.getMaxHealth()));

      if (attacker instanceof Player) {
        ((Player) attacker).spigot().sendMessage(ChatMessageType.ACTION_BAR,
            TextComponent.fromLegacyText(ChatColor.GREEN + this.getDisplayName().toUpperCase()));
      }
    }
  }

  @Override
  public void onDefend(EntityDamageByEntityEvent event, int level, Entity attacker, LivingEntity defender) {
    // Vampirism only triggers on attack.
  }

}
