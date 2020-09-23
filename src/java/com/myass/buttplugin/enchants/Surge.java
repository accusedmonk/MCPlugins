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

public class Surge extends CombatEnchant {
    private Random random = new Random();

    public Surge() {
        super("Surge", 10.0, 3, new ArrayList<Material>() {
            {
                add(Material.SHIELD);
            }
        });
    }

    @Override
    public void onAttack(EntityDamageByEntityEvent event, int level, LivingEntity attacker, Entity defender) {
        // Nothing on attack
    }

    @Override
    public void onDefend(EntityDamageByEntityEvent event, int level, Entity attacker, LivingEntity defender) {
        double chance = level * 2 + 3.0;
        if (random.nextDouble() * 100 < chance) {
            defender.setAbsorptionAmount(4.0);
            if (attacker instanceof Player) {
                ((Player) attacker).spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        TextComponent.fromLegacyText(ChatColor.GREEN + this.getDisplayName().toUpperCase()));
            }
        }

        defender.setAbsorptionAmount(defender.getAbsorptionAmount());
    }

}