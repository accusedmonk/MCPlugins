package com.myass.buttplugin.listeners;

import java.util.List;

import com.myass.buttplugin.helpers.EnchantManager;
import com.myass.buttplugin.models.CombatEnchant;
import com.myass.buttplugin.models.CustomEnchant;
import com.myass.buttplugin.models.CustomEnchantInstance;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener implements Listener {

    @EventHandler
    public void onCombatEvent(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity defender = event.getEntity();

        // Handle Attacker Enchants
        if (attacker instanceof LivingEntity) {
            LivingEntity livingAttacker = (LivingEntity) attacker;
            List<CustomEnchantInstance> customEnchants = EnchantManager.getEntityEnchants(livingAttacker);
            for (CustomEnchantInstance customEnchantInstance : customEnchants) {
                int level = customEnchantInstance.getLevel();
                CustomEnchant customEnchant = customEnchantInstance.getEnchant();

                if (customEnchant instanceof CombatEnchant) {
                    ((CombatEnchant) customEnchant).onAttack(event, level, livingAttacker, defender);
                }
            }
        }

        // Handle Defender Enchants
        if (defender instanceof LivingEntity) {
            LivingEntity livingDefender = (LivingEntity) defender;
            List<CustomEnchantInstance> customEnchants = EnchantManager.getEntityEnchants(livingDefender);
            for (CustomEnchantInstance customEnchantInstance : customEnchants) {
                int level = customEnchantInstance.getLevel();
                CustomEnchant customEnchant = customEnchantInstance.getEnchant();

                if (customEnchant instanceof CombatEnchant) {
                    ((CombatEnchant) customEnchant).onDefend(event, level, attacker, livingDefender);
                }
            }
        }
    }
}