package com.myass.buttplugin;

import com.myass.buttplugin.commands.CEnchantCommand;
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

        new EnchantManager();
        registerCommands();
        registerEvents();

        instance = this;
    }

    @Override
    public void onDisable() {
        System.out.println("Unsuck it");
    }

    public static ButtPlugin getInstance() {
        return instance;
    }

    private void registerCommands() {
        this.getCommand("cenchant").setExecutor(new CEnchantCommand());
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new CombatListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnchantListener(), this);
    }
}
