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

public class Critical extends CombatEnchant {
    private Random random = new Random();

    public Critical() {
        super("Critical", 10.0, 5, new ArrayList<Material>() {
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
        double chance = level * 3 + 7.0;
        if (random.nextDouble() * 100 < chance) {
            event.setDamage(event.getDamage() * 1.5);

            if (attacker instanceof Player) {
                ((Player) attacker).spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        TextComponent.fromLegacyText(ChatColor.GREEN + this.getDisplayName().toUpperCase()));
            }
        }
    }

    @Override
    public void onDefend(EntityDamageByEntityEvent event, int level, Entity attacker, LivingEntity defender) {
        // Nothing on defense
    }

}
