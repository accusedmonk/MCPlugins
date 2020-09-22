package com.myass.buttplugin;

import com.myass.buttplugin.enchants.Vampirism;
import com.myass.buttplugin.helpers.EnchantManager;
import com.myass.buttplugin.listeners.CombatListener;
import com.myass.buttplugin.listeners.EnchantListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ButtPlugin extends JavaPlugin {

    private static ButtPlugin instance;

    @Override
    public void onEnable() {
        System.out.println("Suck it");
        registerEnchantments();

        Bukkit.getPluginManager().registerEvents(new CombatListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnchantListener(), this);
        instance = this;
    }

    @Override
    public void onDisable() {
        System.out.println("Unsuck it");
    }

    private void registerEnchantments() {
        EnchantManager.registerCustomEnchant(new Vampirism());
    }

    public static ButtPlugin getInstance() {
        return instance;
    }
}
