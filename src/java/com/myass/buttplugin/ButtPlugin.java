package com.myass.buttplugin;

import com.myass.buttplugin.listeners.EnchantListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ButtPlugin extends JavaPlugin{
    @Override
    public void onEnable() {
        System.out.println("Suck it");

        Bukkit.getPluginManager().registerEvents(new EnchantListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Unsuck it");
    }
}
